package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.AlbumEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.service.impl.AlbumService;
import vn.iostar.springbootbackend.service.impl.SongService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumEntity>> getAllAlbums() {
        List<AlbumEntity> albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<AlbumEntity> getAlbumById(@PathVariable("id") Long id) {
        Optional<AlbumEntity> foundAlbum = albumService.getAlbumById(id);
        if (foundAlbum.isPresent()) {
            return ResponseEntity.ok(foundAlbum.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find album with id: " + id);
    }
    @GetMapping("/album/{id}/songs")
    public ResponseEntity<List<SongEntity>> getSongByAlbumId(@PathVariable("id") Long id) {
        Optional<AlbumEntity> optAlbum = albumService.getAlbumById(id);
        if(optAlbum.isPresent()) {
            List<SongEntity> songs = songService.getSongByAlbum(optAlbum.get());
            return ResponseEntity.ok(songs);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find album");
    }

    @GetMapping("/albums/search")
    public ResponseEntity<List<AlbumEntity>> getAlbumByKeyword(@RequestParam("name") String keyword) {
        List<AlbumEntity> foundAlbums = albumService.getAlbumByKeyword(keyword);
        if (foundAlbums.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No result");
        }
        return ResponseEntity.ok(foundAlbums);
    }
}
