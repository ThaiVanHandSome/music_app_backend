package vn.iostar.springbootbackend.service.impl;

import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.AlbumEntity;
import vn.iostar.springbootbackend.repository.AlbumRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<AlbumEntity> getAllAlbums() {
        return albumRepository.findAll();
    }
    public Optional<AlbumEntity> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    public List<AlbumEntity> getAlbumByIdArtist(Long idArtist) {
        return albumRepository.getAlbumByIdArtist(idArtist);
    }

    public List<AlbumEntity> getAlbumByKeyword(String keyword) {
        return albumRepository.findByNameContaining(keyword);
    }
}
