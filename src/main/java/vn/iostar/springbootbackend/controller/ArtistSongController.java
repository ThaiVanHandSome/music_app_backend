package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.springbootbackend.service.impl.ArtistSongService;


@RestController
@RequestMapping("/api/v1")
public class ArtistSongController {
    @Autowired
    private ArtistSongService artistSongService;


    // Get Songs by ArtistId
    @GetMapping("/{artistId}/songs")
    public ResponseEntity<?> getAllSongsByArtistId(@PathVariable Long artistId) {
        return ResponseEntity.ok(artistSongService.findAllSongsByArtistId(artistId));
    }
}
