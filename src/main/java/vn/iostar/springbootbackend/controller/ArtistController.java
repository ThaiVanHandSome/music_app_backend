package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.AlbumEntity;
import vn.iostar.springbootbackend.entity.ArtistEntity;
import vn.iostar.springbootbackend.service.impl.AlbumService;
import vn.iostar.springbootbackend.service.impl.ArtistService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @Autowired
    AlbumService albumService;

    @GetMapping("/artists")
    public ResponseEntity<List<ArtistEntity>> getAllArtists() {
        List<ArtistEntity> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<ArtistEntity> getArtistById(@PathVariable("id") Long id) {
        Optional<ArtistEntity> foundArtist = artistService.getArtistById(id);
        if (foundArtist.isPresent()) {
            return ResponseEntity.ok(foundArtist.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find artist with id: " + id);
    }

    @GetMapping("/artist/{id}/albums")
    public ResponseEntity<List<AlbumEntity>> getAlbumByIdArtist(@PathVariable("id") Long idArtist) {
        Optional<ArtistEntity> foundArtist = artistService.getArtistById(idArtist);
        if (foundArtist.isPresent()) {
            List<AlbumEntity> albums = albumService.getAlbumByIdArtist(idArtist);
            return ResponseEntity.ok(albums);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find albums of this artist");
    }

    @GetMapping("/artists/search")
    public ResponseEntity<List<ArtistEntity>> getArtistByKeyword (@RequestParam("name") String keyword) {
        List<ArtistEntity> foundArtists = artistService.getArtistByKeyword(keyword);
        if (foundArtists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No result");
        }
        return ResponseEntity.ok(foundArtists);
    }
}
