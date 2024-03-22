package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.springbootbackend.entity.PlaylistEntity;
import vn.iostar.springbootbackend.entity.UserEntity;
import vn.iostar.springbootbackend.service.impl.PlaylistService;
import vn.iostar.springbootbackend.service.impl.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserService userService;

//    public List<PlaylistEntity> getPlaylistByUser(@PathVariable("id") Long id) {
//        Optional<UserEntity>
//    }
}
