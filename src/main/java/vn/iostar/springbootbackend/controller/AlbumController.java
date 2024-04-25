package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.Album;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.response.Response;
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
    public ResponseEntity<?> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        Response res = new Response(true, false, "Get Albums Successfully!", albums);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<?> getAlbumById(@PathVariable("id") Long id) {
        Optional<Album> foundAlbum = albumService.getAlbumById(id);
        if (foundAlbum.isPresent()) {
            Response res = new Response(true, false, "Get Album Successfully!", foundAlbum.get());
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find album with id: " + id);
    }
    @GetMapping("/album/{id}/songs")
    public ResponseEntity<?> getSongByAlbumId(@PathVariable("id") Long id) {
        Optional<Album> optAlbum = albumService.getAlbumById(id);
        if(optAlbum.isPresent()) {
            List<Song> songs = songService.getSongByAlbum(optAlbum.get());
            Response res = new Response(true, false, "Get Songs Of Album Successfully!", songs);
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find album");
    }

    @GetMapping("/albums/search")
    public ResponseEntity<?> getAlbumByKeyword(@RequestParam("name") String keyword) {
        List<Album> foundAlbums = albumService.getAlbumByKeyword(keyword);
        if (foundAlbums.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No result");
        }
        Response res = new Response(true, false, "Successfully!", foundAlbums);
        return ResponseEntity.ok(res);
    }
}
