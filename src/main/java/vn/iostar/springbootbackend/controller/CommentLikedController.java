package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.impl.CommentLikedService;
import vn.iostar.springbootbackend.service.impl.SongLikedService;

@RestController
@RequestMapping("/api/v1")
public class CommentLikedController {

    @Autowired
    private CommentLikedService commentLikedService;

    @GetMapping("/song/isUserLikedComment")
    public ResponseEntity<?> isUserLikedComment(@RequestParam("commentId") Long commentId, @RequestParam("userId") Long userId) {
        boolean isLiked = commentLikedService.isUserLikedComment(commentId, userId);
        Response res = new Response(true, false, "Check Successfully!", isLiked);
        return ResponseEntity.ok(res);
    }

    @PostMapping("song/comment/like")
    public ResponseEntity<?> toggleLike(@RequestParam("commentId") Long commentId, @RequestParam("userId") Long userId) {
        commentLikedService.toggleLike(commentId, userId);
        boolean isLiked = commentLikedService.isUserLikedComment(commentId, userId);
        Response res = new Response(true, false, "Successfully!", isLiked);
        return ResponseEntity.ok(res);
    }
}

