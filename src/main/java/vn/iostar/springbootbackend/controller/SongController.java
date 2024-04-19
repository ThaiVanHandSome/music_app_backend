package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.Artist;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.response.Response;
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
    public ResponseEntity<?> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        Response res = new Response(true, false, "Get Songs Successfully!", songs);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<?> getSongById(@PathVariable("id") Long id) {
        Optional<Song> optSong = songService.getSongById(id);
        if(optSong.isPresent()) {
            Response res = new Response(true, false, "Get Song Successfully!", optSong.get());
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find song");
    }

    @GetMapping("/songs/query")
    public ResponseEntity<?> getSongsByKeyword(@RequestParam("p") String query) {
        List<Song> songs = songService.getSongsByKeyWord(query);
        Response res = new Response(true, false, "Find Songs Successfully!", songs);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/song/{id}/view")
    public ResponseEntity<?> increaseViewOfSong(@PathVariable("id") Long id) {
        Optional<Song> optSong = songService.getSongById(id);
        if(optSong.isPresent()) {
            songService.increaseViewOfSong(id);
            Response res = new Response(true, false, "Successfully!", songService.getSongById(id).get());
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find song");
    }

    @GetMapping("/song/{songId}/artists")
    public ResponseEntity<?> getArtistBySongId(@PathVariable Long songId) {
        List<Artist> artists = artistSongService.findArtistBySongId(songId);
        Response res = new Response(true, false, "Get Artists Successfully!", artists);
        return ResponseEntity.ok(res);
    }
}
