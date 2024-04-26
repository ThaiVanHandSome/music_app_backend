package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.Album;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.AlbumService;
import vn.iostar.springbootbackend.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ArtistController {
    @Autowired
    UserService userService;

    @Autowired
    AlbumService albumService;

    @GetMapping("/artist/{id}")
    public ResponseEntity<?> getArtistById(@PathVariable("id") Long id) {
        Optional<User> foundArtist = userService.findByIdUser(id);
        if (foundArtist.isPresent()) {
            Response res = new Response(true, false, "Get Artist Successfully!", foundArtist.get());
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find artist with id: " + id);
    }

    @GetMapping("/artist/{id}/albums")
    public ResponseEntity<?> getAlbumByIdArtist(@PathVariable("id") Long idArtist) {
        Optional<User> foundArtist = userService.findByIdUser(idArtist);
        if (foundArtist.isPresent()) {
            List<Album> albums = albumService.getAlbumByIdArtist(idArtist);
            Response res = new Response(true, false, "Get Albums Of Artist Successfully!", albums);
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find albums of this artist");
    }

    @GetMapping("/artists/search")
    public ResponseEntity<?> getArtistByKeyword (@RequestParam("name") String keyword) {
        List<User> foundArtists = userService.getArtistByKeyword(keyword);
        if (foundArtists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No result");
        }
        Response res = new Response(true, false, "Search Successfully!", foundArtists);
        return ResponseEntity.ok(res);
    }
}
