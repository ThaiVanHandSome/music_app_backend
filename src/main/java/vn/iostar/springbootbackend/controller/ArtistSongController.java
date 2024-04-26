package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.ArtistSongService;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ArtistSongController {
    @Autowired
    private ArtistSongService artistSongService;

    // Get Songs by ArtistId
    @GetMapping("/artist/{artistId}/songs")
    public ResponseEntity<?> getAllSongsByArtistId(@PathVariable Long artistId) {
        List<Song> songs = artistSongService.findAllSongsByArtistId(artistId);
        Response res = new Response(true, false, "Get Songs Of Artist Successfully!", songs);
        return ResponseEntity.ok(res);
    }
}
