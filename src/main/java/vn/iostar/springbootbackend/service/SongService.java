package vn.iostar.springbootbackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.iostar.springbootbackend.entity.Album;
import vn.iostar.springbootbackend.entity.ArtistSong;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.model.ArtistModel;
import vn.iostar.springbootbackend.model.SongModel;
import vn.iostar.springbootbackend.repository.SongRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private Cloudinary cloudinary;

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongModel> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return convertToSongModel(songs);
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }

    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }

    public List<Song> getSongViewHigherThanSomeValue(int val) {
        return songRepository.findByViewsGreaterThan(val);
    }

    public List<Song> getSongByAlbum(Album album) {
        return songRepository.findByAlbum(album);
    }

    public List<SongModel> getSongsByKeyWord(String keyword) {
        List<Song> songs = songRepository.findByNameContaining(keyword);
        if (!songs.isEmpty()) {
            List<SongModel> songModels = new ArrayList<>();
            for (Song song : songs) {
                SongModel songModel = new SongModel();
                BeanUtils.copyProperties(song, songModel);
                songModels.add(songModel);
            }
            return songModels;
        } else {
            return new ArrayList<>();
        }
    }

    public void increaseViewOfSong(Long id) {
        songRepository.incrementViewCount(id);
    }

    public Optional<Song> getSongbyId(Long id){
        return  songRepository.findById(id);
    }

    public Page<Song> getSongsByMostViews(Pageable pageable) { return songRepository.findByOrderByViewsDesc(pageable); };

    public Page<Song> getSongsByMostLikes(Pageable pageable) { return songRepository.findSongsByMostLikes(pageable); };

    public Page<Song> getSongsByDayCreated(Pageable pageable) { return songRepository.findByOrderByDayCreatedDesc(pageable); };

    public List<SongModel> convertToSongModel(List<Song> songs) {
        List<SongModel> songModels = new ArrayList<>();
        for (Song song : songs) {
            SongModel songModel = new SongModel();
            songModel.setIdSong(song.getIdSong());
            songModel.setName(song.getName());
            songModel.setViews(song.getViews());
            songModel.setDayCreated(song.getDayCreated());
            songModel.setResource(song.getResource());
            songModel.setImage(song.getImage());
            songModel.setArtistId(song.getArtistSongs().get(0).getArtist().getIdUser());
            songModel.setArtistName(song.getArtistSongs().get(0).getArtist().getNickname());
            songModels.add(songModel);
        }
        return songModels;
    }

    public long countSongs() {
        return songRepository.count();
    }

    public String uploadAudio(MultipartFile audioFile) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(audioFile.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        return (String) result.get("url");
    }

    public void deleteSong(Song song) {
        songRepository.delete(song);
    }

    public List<SongModel> searchSong(String query){
        List<Song> songs = songRepository.searchSong(query);
        if (!songs.isEmpty()) {
            List<SongModel> songModels = new ArrayList<>();
            for (Song song : songs) {
                SongModel songModel = new SongModel();
                BeanUtils.copyProperties(song, songModel);
                songModels.add(songModel);
            }
            return songModels;
        } else {
            return new ArrayList<>();
        }
    }
}
