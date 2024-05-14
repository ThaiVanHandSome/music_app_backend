package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.iostar.springbootbackend.entity.Role;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.model.PlaylistModel;
import vn.iostar.springbootbackend.model.SongModel;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.ImageService;
import vn.iostar.springbootbackend.service.PlaylistService;
import vn.iostar.springbootbackend.service.SongLikedService;
import vn.iostar.springbootbackend.service.UserService;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
  
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private ImageService imageService;


    @Autowired
    private SongLikedService songLikedService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        Response res = new Response();
        res.setError(false);
        res.setSuccess(true);
        res.setMessage("Get All Users Successfully!");
        res.setData(userService.findAll());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/users/count")
    public ResponseEntity<?> getUserCount() {
        Response res = new Response();
        res.setError(false);
        res.setSuccess(true);
        res.setMessage("Get User Count Successfully!");
        res.setData(userService.countUsers());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/users/artists/count")
    public ResponseEntity<?> getArtistsCount() {
        Response res = new Response();
        res.setError(false);
        res.setSuccess(true);
        res.setMessage("Get User Count Successfully!");
        res.setData(userService.countArtists());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<?> getAllUsersNotAdmin() {
        Response res = new Response();
        res.setError(false);
        res.setSuccess(true);
        res.setMessage("Get All Users Successfully!");
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ARTIST);
        roles.add(Role.USER);
        res.setData(userService.findByRoles(roles));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable("idUser") Long idUser) {
        Response res = new Response();
        res.setError(false);
        res.setSuccess(true);
        res.setMessage("Get All Users Successfully!");
        res.setData(userService.findByIdUser(idUser));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestParam("email") String email) {
        Response res = new Response();
        res.setError(false);
        res.setSuccess(true);
        res.setMessage("Get User Successfully!");
        res.setData(userService.getUserByEmail(email));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/user/upload")
    public ResponseEntity<?> uploadAvatar(@RequestPart("imageFile") MultipartFile imageFile, @RequestPart Long idUser) {
        try {
            String imageUrl = imageService.uploadImage(imageFile);
            Optional<User> user = userService.findByIdUser(idUser);
            if (user.isEmpty() || !Objects.equals(user.get().getIdUser(), idUser)) {
                return (ResponseEntity<?>) ResponseEntity.notFound();
            }
            User userEntity = user.get();
            userEntity.setAvatar(imageUrl);
            userService.updateUserInformation(userEntity);
            Response response = new Response(true, false, "Update Success!", user);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/user/{idUser}")
    public ResponseEntity<?> updateUserByFields(@PathVariable("idUser") Long idUser, @RequestBody Map<String, String> reqBody) {
        String firstName = reqBody.get("firstName");
        String lastName = reqBody.get("lastName");
        int gender = Integer.parseInt(reqBody.get("gender"));
        Optional<User> user = userService.findByIdUser(idUser);
        if (user.isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
        User userEntity = user.get();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userService.updateUserInformation(userEntity);
        Response response = new Response(true, false, "Update Success!", user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/user/{idUser}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable("idUser") Long idUser, @RequestBody Map<String, String> reqBody) {
        String password = reqBody.get("password");
        return ResponseEntity.ok(userService.changePassword(idUser, password));
    }

    @PatchMapping("/user/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> reqBody) {
        String password = reqBody.get("password");
        String email = reqBody.get("email");
        return ResponseEntity.ok(userService.changePasswordForgot(email, password));
    }
  
    @GetMapping("/user/{id_user}/playlists")
    public ResponseEntity<?> getPlaylistsByIdUser(@PathVariable("id_user") Long idUser) {
        List<PlaylistModel> playlists = playlistService.getPlaylistsByIdUser(idUser);
        Response response = new Response(true, false, "Get Playlists Success!", playlists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id_user}/liked-songs")
    public ResponseEntity<?> getLikedSongsByIdUser(@PathVariable("id_user") Long idUser) {
        List<SongModel> songs = songLikedService.getLikedSongsByIdUser(idUser);
        Response response = new Response(true, false, "Get Liked Songs Success!", songs);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/{id_user}/not-liked-songs")
    public ResponseEntity<?> getNotLikedSongsByIdUser(@PathVariable("id_user") Long idUser) {
        List<SongModel> songs = songLikedService.getNotLikedSongsByIdUser(idUser);
        Response response = new Response(true, false, "Get Not Liked Songs Success!", songs);
        return ResponseEntity.ok(response);
    }
}
