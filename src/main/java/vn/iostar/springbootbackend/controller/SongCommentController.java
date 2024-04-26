package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.SongComment;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.SongCommentService;
import vn.iostar.springbootbackend.service.SongService;

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
    public ResponseEntity<?> getCommentsBySongId(@PathVariable Long id_song) {
        Optional<Song> song = songService.getSongbyId(id_song);
        List<SongComment> comments = songCommentService.findAllComentsBySong(song);
        Response res = new Response(true, false, "Get Comments Of Song Successfully!", comments);
        return ResponseEntity.ok(res);
    }

    // Save Comment
    @PostMapping("/song/{id_song}/comments")
    public ResponseEntity<?> createComment(@RequestBody SongComment newComment) {
        LocalDateTime commentDay = LocalDateTime.now();
        newComment.setDayCommented(commentDay);
        SongComment savedComment = songCommentService.saveComment(newComment);
        Response res = new Response(true, false, "Add Comment Successfully!", newComment);
        return ResponseEntity.ok(res);
    }

    // Get likes
    @GetMapping("/song/comment/{id_comment}/likes")
    public ResponseEntity<?> getLikesOfComment(@PathVariable Long id_comment){
        Optional<SongComment> comment = songCommentService.findCommentByIdComment(id_comment);
        if(comment.isPresent()) {
            Response res = new Response(true, false, "Get Likes Of Comment Successfully!",  comment.get().getLikes());
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find comment with id: " + id_comment);
    }
}
