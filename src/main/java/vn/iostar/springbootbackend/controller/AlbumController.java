package vn.iostar.springbootbackend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.entity.Album;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.model.ResponseMessage;
import vn.iostar.springbootbackend.model.SongModel;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.AlbumService;
import vn.iostar.springbootbackend.service.ImageService;
import vn.iostar.springbootbackend.service.SongService;
import vn.iostar.springbootbackend.service.UserService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/albums")
    public ResponseEntity<?> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        Response res = new Response(true, false, "Get Albums Successfully!", albums);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<?> getAlbumById(@PathVariable("id") Long id) {
        Optional<Album> foundAlbum = albumService.getAlbumById(id);
        if (foundAlbum.isPresent()) {
            Response res = new Response(true, false, "Get Album Successfully!", foundAlbum.get());
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find album with id: " + id);
    }

    @DeleteMapping("/album/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable("id") Long id) {
        Optional<Album> foundAlbum = albumService.getAlbumById(id);
        if (foundAlbum.isPresent()) {
            albumService.deleteAlbum(foundAlbum.get());
            ResponseMessage responseMessage = new ResponseMessage("Delete Successfully!", true, false);
            return ResponseEntity.ok(responseMessage);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find album with id: " + id);
    }

    @GetMapping("/album/{id}/songs")
    public ResponseEntity<?> getSongByAlbumId(@PathVariable("id") Long id) {
        Optional<Album> optAlbum = albumService.getAlbumById(id);
        if(optAlbum.isPresent()) {
            List<Song> songs = songService.getSongByAlbum(optAlbum.get());
            List<SongModel> songModels = new ArrayList<>();
            for(Song song : songs) {
                SongModel songModel = new SongModel();
                BeanUtils.copyProperties(song, songModel);
                songModel.setCntComments(song.getSongComments().size());
                songModel.setCntLikes(song.getSongLikeds().size());
                songModels.add(songModel);
            }
            Response res = new Response(true, false, "Get Songs Of Album Successfully!", songModels);
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find album");
    }

    @GetMapping("/albums/search")
    public ResponseEntity<?> getAlbumByKeyword(@RequestParam("name") String keyword) {
        List<Album> foundAlbums = albumService.getAlbumByKeyword(keyword);
        if (foundAlbums.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No result");
        }
        Response res = new Response(true, false, "Successfully!", foundAlbums);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/album/upload")
    public ResponseEntity<?> uploadAlbum(@RequestPart("image") MultipartFile image, @RequestPart("idArtist") Long idArtist, @RequestPart("albumName") String albumName, @RequestPart("listSong") String listSong) throws IOException {
        Album album = new Album();
        albumName = albumName.replace("\"", "");
        album.setName(albumName);
        album.setUser(userService.getUserById(idArtist).get());
        String imageUrl = imageService.uploadImage(image);
        album.setImage(imageUrl);
        album.setDayCreated(LocalDateTime.now());
        Album savedAlbum = albumService.saveAlbum(album);

        System.out.println(listSong);
        listSong = listSong.substring(1, listSong.length() - 1);
        String[] nums = listSong.split(",");
        List<Long> selectedSongs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
            selectedSongs.add(Long.parseLong(nums[i]));
        }


        for(Long songId : selectedSongs) {
            Optional<Song> foundSong = songService.getSongById(songId);
            if(foundSong.isPresent()) {
                Song song = foundSong.get();
                song.setAlbum(savedAlbum);
                songService.saveSong(song);
            }
        }

        ResponseMessage res = new ResponseMessage();
        res.setMessage("Upload Album Successfully!");
        res.setError(false);
        res.setSuccess(true);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/albums/artist/{idArtist}/count")
    public ResponseEntity<?> getCountOfAlbums(@PathVariable("idArtist") Long idArtist) {
        Response res = new Response();
        res.setSuccess(true);
        res.setError(false);
        res.setMessage("Get Quantity Successfully!");
        res.setData(albumService.countAlbumsByArtistId(idArtist));
        return ResponseEntity.ok(res);
    }
}
