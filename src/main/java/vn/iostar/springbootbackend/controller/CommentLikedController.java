package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.service.impl.CommentLikedService;
import vn.iostar.springbootbackend.service.impl.SongLikedService;

@RestController
@RequestMapping("/api/v1")
public class CommentLikedController {

    @Autowired
    private CommentLikedService commentLikedService;

    @GetMapping("/song/isUserLikedComment/commentId={commentId}&userId={userId}")
    public ResponseEntity<?> isUserLikedComment(@PathVariable Long commentId, @PathVariable Long userId) {
        return ResponseEntity.ok(commentLikedService.isUserLikedComment(commentId, userId));
    }

    @PostMapping("song/comment/like/commentId={commentId}&userId={userId}")
    public ResponseEntity<?> toggleLike(@PathVariable Long commentId, @PathVariable Long userId) {
        commentLikedService.toggleLike(commentId, userId);
        boolean isLiked = commentLikedService.isUserLikedComment(commentId, userId);
        return ResponseEntity.ok(isLiked);
    }
}

