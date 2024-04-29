package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.model.PlaylistModel;
import vn.iostar.springbootbackend.model.SongModel;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.PlaylistService;
import vn.iostar.springbootbackend.service.SongLikedService;
import vn.iostar.springbootbackend.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
  
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private SongLikedService songLikedService;
  
    @PatchMapping("/{idUser}")
    public ResponseEntity<?> updateUserByFields(@PathVariable("idUser") Long idUser, @RequestBody Map<String, Object> fields) {
        User user = userService.updateUserByFields(idUser, fields);
        Response response = new Response(true, false, "Update Success!", user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{idUser}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable("idUser") Long idUser, @RequestBody Map<String, String> reqBody) {
        String password = reqBody.get("password");
        return ResponseEntity.ok(userService.changePassword(idUser, password));
    }

    @PatchMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> reqBody) {
        String password = reqBody.get("password");
        String email = reqBody.get("email");
        return ResponseEntity.ok(userService.changePasswordForgot(email, password));
    }
  
    @GetMapping("{id_user}/playlists")
    public ResponseEntity<?> getPlaylistsByIdUser(@PathVariable("id_user") Long idUser) {
        List<PlaylistModel> playlists = playlistService.getPlaylistsByIdUser(idUser);
        Response response = new Response(true, false, "Get Playlists Success!", playlists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id_user}/liked-songs")
    public ResponseEntity<?> getLikedSongsByIdUser(@PathVariable("id_user") Long idUser) {
        List<SongModel> songs = songLikedService.getLikedSongsByIdUser(idUser);
        Response response = new Response(true, false, "Get Liked Songs Success!", songs);
        return ResponseEntity.ok(response);
    }
    @GetMapping("{id_user}/not-liked-songs")
    public ResponseEntity<?> getNotLikedSongsByIdUser(@PathVariable("id_user") Long idUser) {
        List<SongModel> songs = songLikedService.getNotLikedSongsByIdUser(idUser);
        Response response = new Response(true, false, "Get Not Liked Songs Success!", songs);
        return ResponseEntity.ok(response);
    }
}
