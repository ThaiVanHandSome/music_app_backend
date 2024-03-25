package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.entity.SongCommentEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.service.impl.SongCommentService;
import vn.iostar.springbootbackend.service.impl.SongService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SongCommentController {
    @Autowired
    private SongCommentService songCommentService;

    @Autowired
    private SongService songService;

    // Get Comments by Song
    @GetMapping("/song/{id_song}/comments")
    public ResponseEntity<List<SongCommentEntity>> getCommentsBySongId(@PathVariable Long id_song) {
        Optional<SongEntity> song = songService.getSongbyId(id_song);
        List<SongCommentEntity> comments = songCommentService.findAllComentsBySong(song);
        return ResponseEntity.ok(comments);
    }

    // Save Comment
    @PostMapping("/song/{id_song}/comments")
    public ResponseEntity<SongCommentEntity> createComment(@RequestBody SongCommentEntity newComment) {
        LocalDateTime commentDay = LocalDateTime.now();
        newComment.setDayCommented(commentDay);
        SongCommentEntity savedComment = songCommentService.saveComment(newComment);
        return ResponseEntity.ok(savedComment);
    }

    // Get likes
    @GetMapping("/song/comment/{id_comment}/likes")
    public int getLikesOfComment(@PathVariable Long id_comment){
        Optional<SongCommentEntity> comment = songCommentService.findCommentByIdComment(id_comment);
        return comment.isPresent() ? comment.get().getLikes() : -1;
    }
}
