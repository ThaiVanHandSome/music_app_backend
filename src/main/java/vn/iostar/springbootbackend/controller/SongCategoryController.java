package vn.iostar.springbootbackend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.springbootbackend.entity.SongCategory;
import vn.iostar.springbootbackend.model.SongCategoryModel;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.SongCategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SongCategoryController {
    @Autowired
    private SongCategoryService songCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        List<SongCategoryModel> listSongCategoryModel = new ArrayList<>();
        List<SongCategory> songCategories = songCategoryService.findAll();
        for (SongCategory songCategory : songCategories) {
            SongCategoryModel songCategoryModel = new SongCategoryModel();
            BeanUtils.copyProperties(songCategory, songCategoryModel);
            listSongCategoryModel.add(songCategoryModel);
        }
        Response res = new Response();
        res.setData(listSongCategoryModel);
        res.setSuccess(true);
        res.setError(false);
        res.setMessage("Get Categories Successfully");
        return ResponseEntity.ok(res);
    }
}
