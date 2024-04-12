package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.impl.SongLikedService;

@RestController
@RequestMapping("/api/v1/songLiked")
public class SongLikedController {

    @Autowired
    private SongLikedService songLikedService;

    @GetMapping("/likedCountById/{songId}")
    public ResponseEntity<?> LikeCountById(@PathVariable("songId") Long songId) {
        Long cnt = songLikedService.countLikesBySongId(songId);
        Response res = new Response(true, false, "Get Liked Count Successfully!", cnt);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/isUserLikedSong")
    public ResponseEntity<?> isUserLikedSong(@RequestParam("songId") Long songId, @RequestParam("userId") Long userId) {
        boolean isLikded = songLikedService.isUserLikedSong(songId, userId);
        Response res = new Response(true, false, "Successfully!", isLikded);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/toggleLike")
    public ResponseEntity<?> toggleLike(@RequestParam("songId") Long songId, @RequestParam("userId") Long userId) {
        songLikedService.toggleLike(songId, userId);
        boolean isLiked = songLikedService.isUserLikedSong(songId, userId);
        Response res = new Response(true, false, "Successfully!", isLiked);
        return ResponseEntity.ok(res);
    }


}
