package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.AlbumEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.service.impl.AlbumService;
import vn.iostar.springbootbackend.service.impl.ArtistSongService;
import vn.iostar.springbootbackend.service.impl.SongService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SongController {
    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistSongService artistSongService;

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

    @GetMapping("/song")
    public ResponseEntity<List<SongEntity>> getSongViewHigherThanSomeValue(@RequestParam("queryView") int val) {
        if(val < 0) return ResponseEntity.badRequest().build();
        List<SongEntity> songs = songService.getSongViewHigherThanSomeValue(val);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/songs/query")
    public ResponseEntity<List<SongEntity>> getSongsByKeyword(@RequestParam("p") String query) {
        List<SongEntity> songs = songService.getSongsByKeyWord(query);
        return ResponseEntity.ok(songs);
    }

    @PatchMapping("/song/{id}/view")
    public ResponseEntity<SongEntity> increaseViewOfSong(@PathVariable("id") Long id) {
        Optional<SongEntity> optSong = songService.getSongById(id);
        if(optSong.isPresent()) {
            songService.increaseViewOfSong(id);
            return ResponseEntity.ok(songService.getSongById(id).get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find song");
    }

    @GetMapping("/song/{songId}/artists")
    public ResponseEntity<?> getArtistBySongId(@PathVariable Long songId) {
        return ResponseEntity.ok(artistSongService.findArtistBySongId(songId));
    }
}
