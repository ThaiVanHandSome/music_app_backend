package vn.iostar.springbootbackend.service.impl;

import org.springframework.stereotype.Service;
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
}
