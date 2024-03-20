package vn.iostar.springbootbackend.service.impl;

import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.AlbumEntity;
import vn.iostar.springbootbackend.repository.AlbumRepository;

import java.util.Optional;

@Service
public class AlbumService {
    private AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Optional<AlbumEntity> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }
}
