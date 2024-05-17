package vn.iostar.springbootbackend.controller;

import jdk.jfr.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import vn.iostar.springbootbackend.embededId.ArtistSongId;
import vn.iostar.springbootbackend.entity.*;
import vn.iostar.springbootbackend.model.ResponseMessage;
import vn.iostar.springbootbackend.model.SongModel;
import vn.iostar.springbootbackend.model.SongUpload;
import vn.iostar.springbootbackend.repository.SongRepository;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SongController {
    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ArtistSongService artistSongService;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongCategoryService songCategoryService;
    @Autowired
    private UserService userService;


    @GetMapping("/songs")
    public ResponseEntity<?> getAllSongs() {
        List<SongModel> songModelss = songService.getAllSongs();
        Response res = new Response(true, false, "Get Songs Successfully!", songModelss);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/songs/count")
    public ResponseEntity<?> getSongsCount() {
        long cnt = songService.countSongs();
        Response res = new Response(true, false, "Get Songs Successfully!", cnt);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<?> getSongById(@PathVariable("id") Long id) {
        Optional<Song> optSong = songService.getSongById(id);
        if(optSong.isPresent()) {
            Response res = new Response(true, false, "Get Song Successfully!", optSong.get());
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find song");
    }

    @GetMapping("/songs/query")
    public ResponseEntity<?> getSongsByKeyword(@RequestParam("p") String query) {
        List<Song> songs = songService.getSongsByKeyWord(query);
        Response res = new Response(true, false, "Find Songs Successfully!", songs);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/song/{id}/view")
    public ResponseEntity<?> increaseViewOfSong(@PathVariable("id") Long id) {
        Optional<Song> optSong = songService.getSongById(id);
        if(optSong.isPresent()) {
            songService.increaseViewOfSong(id);
            Response res = new Response(true, false, "Successfully!", songService.getSongById(id).get());
            return ResponseEntity.ok(res);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not find song");
    }

    @GetMapping("/song/{songId}/artists")
    public ResponseEntity<?> getArtistBySongId(@PathVariable Long songId) {
        List<User> artists = artistSongService.findArtistBySongId(songId);
        Response res = new Response(true, false, "Get Artists Successfully!", artists);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/song/most-views")
    public ResponseEntity<?> getSongsByMostViews(Pageable pageable) {
        Page<Song> songs = songService.getSongsByMostViews(pageable);
        System.out.println("pageable: " + pageable.getPageNumber() + " " + pageable.getPageSize());
        Response res = new Response(true, false, "Get Songs By Most Views Successfully!", songs);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/song/most-likes")
    public ResponseEntity<?> getSongsByMostLikes(Pageable pageable) {
        Page<Song> songs = songService.getSongsByMostLikes(pageable);
        System.out.println("pageable: " + pageable.getPageNumber() + " " + pageable.getPageSize());
        Response res = new Response(true, false, "Get Songs By Most Likes Successfully!", songs);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/song/new-released")
    public ResponseEntity<?> getSongsByDayCreated(Pageable pageable) {
        Page<Song> songs = songService.getSongsByDayCreated(pageable);
        System.out.println("pageable: " + pageable.getPageNumber() + " " + pageable.getPageSize());
        Response res = new Response(true, false, "Get Songs By Day Created Successfully!", songs);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/song/upload")
    public ResponseEntity<?> uploadSong(@RequestPart("imageFile") MultipartFile imageFile,
                                        @RequestPart("idArtist") Long idArtist,
                                        @RequestPart("name") String name,
                                        @RequestPart("idSongCategory") Long idSongCategory,
                                        @RequestPart("idAlbum") Long idAlbum,
                                        @RequestPart("resourceFile") MultipartFile resourceFile) throws IOException {
        System.out.println(imageFile.getSize() + " " + imageFile.getOriginalFilename());
        System.out.println(resourceFile.getSize() + " " + resourceFile.getOriginalFilename());
        String image = imageService.uploadImage(imageFile);
        String resource = songService.uploadAudio(resourceFile);
        Song song = new Song();
        song.setName(name);
        song.setImage(image);
        song.setResource(resource);
        song.setDayCreated(LocalDateTime.now());
        Optional<Album> album = albumService.getAlbumById(idAlbum);
        if(album.isPresent()) {
            song.setAlbum(album.get());
        }
        Optional<SongCategory> category = songCategoryService.findById(idSongCategory);
        if(category.isPresent()) {
            song.setSongCategory(category.get());
        }
        Song newSong = songRepository.save(song);
        User artist = userService.findByIdUser(idArtist).get();
        ArtistSongId artistSongId = new ArtistSongId(artist.getIdUser(), newSong.getIdSong());
        artistSongService.save(new ArtistSong(artistSongId, artist, newSong));
        Response res = new Response(true, false, "Uploaded Successfully!", newSong);
        return ResponseEntity.ok(res);
    }
}
