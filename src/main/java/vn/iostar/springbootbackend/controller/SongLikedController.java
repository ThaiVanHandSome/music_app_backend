package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.service.impl.SongLikedService;

@RestController
@RequestMapping("/api/v1/songLiked")
public class SongLikedController {

    @Autowired
    private SongLikedService songLikedService;

    @GetMapping("/likedCountById/{songId}")
    public ResponseEntity<?> LikeCountById(@PathVariable("songId") Long songId) {
        return ResponseEntity.ok(songLikedService.countLikesBySongId(songId));
    }

    @GetMapping("/isUserLikedSong")
    public ResponseEntity<?> isUserLikedSong(@RequestParam("songId") Long songId, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(songLikedService.isUserLikedSong(songId, userId));
    }

    @PostMapping("/toggleLike")
    public ResponseEntity<?> toggleLike(@RequestParam("songId") Long songId, @RequestParam("userId") Long userId) {
        songLikedService.toggleLike(songId, userId);
        boolean isLiked = songLikedService.isUserLikedSong(songId, userId);
        return ResponseEntity.ok(isLiked);
    }


}
