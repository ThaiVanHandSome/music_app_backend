package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.embededId.PlaylistSongId;
import vn.iostar.springbootbackend.entity.PlaylistSong;
import vn.iostar.springbootbackend.model.SongModel;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.PlaylistSongService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/playlistSong")
public class PlaylistSongController {
    @Autowired
    private PlaylistSongService playlistSongService;

    @GetMapping("/{id_playlist}/songs")
    public ResponseEntity<?> findAllSongByPlaylistId(@PathVariable Long id_playlist) {
        List<SongModel> songs = playlistSongService.findAllByPlaylistSongId(id_playlist);
        Response res = new Response(true, false, "Get Songs Of Playlist Successfully!", songs);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id_playlist}/{id_song}")
    public ResponseEntity<?> deleteSongFromPlaylist(@PathVariable Long id_playlist, @PathVariable Long id_song) {
        int isSuccess = playlistSongService.deleteByPlaylistSongId(id_playlist, id_song);
        Response res = new Response(true, false, "Delete Song From Playlist Successfully!", isSuccess);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/{id_playlist}/{id_song}")
    public ResponseEntity<?> addSongToPlaylist(@PathVariable Long id_playlist, @PathVariable Long id_song) {
        PlaylistSong entity = new PlaylistSong();
        entity.setPlaylistSongId(new PlaylistSongId(id_playlist, id_song));
        entity.setDayAdded(LocalDateTime.now());
        playlistSongService.save(entity);
        Response res = new Response(true, false, "Add Song To Playlist Successfully!", null);
        return ResponseEntity.ok(res);
    }
}
