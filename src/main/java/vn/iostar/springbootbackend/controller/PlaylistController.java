package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.entity.Playlist;
import vn.iostar.springbootbackend.entity.PlaylistRequest;
import vn.iostar.springbootbackend.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.springbootbackend.service.impl.PlaylistService;

@RestController
@RequestMapping("/api/v1")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/playlist")
    public ResponseEntity<?> createPlaylist(@RequestBody PlaylistRequest requestBody) {
        Playlist playlist = playlistService.createPlaylist(requestBody);
        return ResponseEntity.ok(playlist);
    }

    @DeleteMapping("/playlist/{id_playlist}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id_playlist) {
        playlistService.deletePlaylist(id_playlist);
        Response response = new Response(true, false,"Playlist deleted", null);
        return ResponseEntity.ok(response);
    }
}
