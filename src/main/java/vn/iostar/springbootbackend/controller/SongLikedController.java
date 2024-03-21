package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.response.ResponseHandler;
import vn.iostar.springbootbackend.service.ISongLikedService;

@RestController
@RequestMapping("/api/v1/songLiked")
public class SongLikedController {

    @Autowired
    private ISongLikedService songLikedService;

    @GetMapping("/likedCountById/{songId}")
    public ResponseEntity<?> LikeCountById(@PathVariable("songId") Long songId) {
        return ResponseHandler.responseBuilder(songLikedService.countLikesBySongId(songId), HttpStatus.OK, "Liked count of song by id: " + songId + " successfully");
    }

    @GetMapping("/isUserLikedSong/songId={songId}&userId={userId}")
    public ResponseEntity<?> isUserLikedSong(@PathVariable("songId") Long songId, @PathVariable("userId") Long userId) {
        return ResponseHandler.responseBuilder(songLikedService.isUserLikedSong(songId, userId), HttpStatus.OK, "Check if user liked song successfully");
    }

    @PostMapping("/toggleLike/songId={songId}&userId={userId}")
    public ResponseEntity<?> toggleLike(@PathVariable("songId") Long songId, @PathVariable("userId") Long userId) {
        songLikedService.toggleLike(songId, userId);
        boolean isLiked = songLikedService.isUserLikedSong(songId, userId);
        return ResponseHandler.responseBuilder(isLiked ? "Liked" : "Unliked", HttpStatus.OK, "Toggle like/unlike song successfully");
    }


}
