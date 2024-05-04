package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.entity.Playlist;
import vn.iostar.springbootbackend.model.PlaylistModel;
import vn.iostar.springbootbackend.model.PlaylistRequest;
import vn.iostar.springbootbackend.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.springbootbackend.service.PlaylistService;

@RestController
@RequestMapping("/api/v1")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/playlist")
    public ResponseEntity<?> createPlaylist(@RequestBody PlaylistRequest requestBody) {
        PlaylistModel playlist = playlistService.createPlaylist(requestBody);
        Response response = new Response(true, false,"Playlist deleted", playlist);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/playlist/{id_playlist}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id_playlist) {
        Playlist playlist = playlistService.getPlaylistById(id_playlist).orElseThrow();
        playlistService.deletePlaylist(playlist);
        Response response = new Response(true, false,"Playlist deleted", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/playlist/{id_playlist}")
    public ResponseEntity<?> getPlaylistById(@PathVariable Long id_playlist) {
        Playlist playlist = playlistService.getPlaylistById(id_playlist).orElseThrow();
        playlistService.setPlaylistImageByFirstSongImage(id_playlist);
        PlaylistModel playlistModel = playlistService.convertToPlaylistModel(playlist);
        Response response = new Response(true, false,"Playlist found", playlistModel);
        return ResponseEntity.ok(response);
    }

}
