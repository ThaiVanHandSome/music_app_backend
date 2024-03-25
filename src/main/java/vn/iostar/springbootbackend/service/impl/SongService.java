package vn.iostar.springbootbackend.service.impl;

import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.AlbumEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.repository.SongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongEntity> getAllSongs() {
        return songRepository.findAll();
    }
    public Optional<SongEntity> getSongById(Long id) {
        return songRepository.findById(id);
    }

    public List<SongEntity> getSongViewHigherThanSomeValue(int val) {
        return songRepository.findByViewsGreaterThan(val);
    }

    public List<SongEntity> getSongByAlbum(AlbumEntity album) {
        return songRepository.findByAlbum(album);
    }

    public List<SongEntity> getSongsByKeyWord(String keyword) {
        return songRepository.findByNameContaining(keyword);
    }

    public void increaseViewOfSong(Long id) {
        songRepository.incrementViewCount(id);
    }

    public Optional<SongEntity> getSongbyId(Long id){
        return  songRepository.findById(id);
    }
}
