package vn.iostar.springbootbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.Album;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.repository.SongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
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

    public List<Song> getSongsByKeyWord(String keyword) {
        return songRepository.findByNameContaining(keyword);
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
}
