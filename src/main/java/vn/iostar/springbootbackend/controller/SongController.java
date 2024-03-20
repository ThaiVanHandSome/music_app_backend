package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.service.impl.SongService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    public ResponseEntity<List<SongEntity>> getAllSongs() {
        List<SongEntity> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<SongEntity> getSongById(@PathVariable("id") Long id) {
        Optional<SongEntity> optSong = songService.getSongById(id);
        if(optSong.isPresent()) {
            return ResponseEntity.ok(optSong.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find song");
    }

}
