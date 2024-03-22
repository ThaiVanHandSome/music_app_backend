package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.embededId.PlaylistSongId;
import vn.iostar.springbootbackend.entity.PlaylistSongEntity;
import vn.iostar.springbootbackend.service.IPlaylistSongService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/playlistSong")
public class PlaylistSongController {
    @Autowired
    private IPlaylistSongService playlistSongService;

    @GetMapping("/findAllSongByPlaylistId/{id_playlist}")
    public ResponseEntity<?> findAllSongByPlaylistId(@PathVariable Long id_playlist) {
        return ResponseEntity.ok(playlistSongService.findAllByPlaylistSongId(id_playlist));
    }

    @DeleteMapping("/deleteSongFromPlaylist/{id_playlist}/{id_song}")
    public ResponseEntity<?> deleteSongFromPlaylist(@PathVariable Long id_playlist, @PathVariable Long id_song) {
        int isSuccess = playlistSongService.deleteByPlaylistSongId(id_playlist, id_song);
        return ResponseEntity.ok(isSuccess);
    }

    @PostMapping("/addSongToPlaylist/{id_playlist}/{id_song}")
    public ResponseEntity<?> addSongToPlaylist(@PathVariable Long id_playlist, @PathVariable Long id_song) {
        PlaylistSongEntity entity = new PlaylistSongEntity();
        entity.setPlaylistSongId(new PlaylistSongId(id_playlist, id_song));
        entity.setDay_added(LocalDateTime.now());
        return ResponseEntity.ok(playlistSongService.save(entity));
    }
}
