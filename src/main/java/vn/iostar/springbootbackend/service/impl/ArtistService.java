package vn.iostar.springbootbackend.service.impl;

import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.ArtistEntity;
import vn.iostar.springbootbackend.repository.ArtistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<ArtistEntity> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<ArtistEntity> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    public List<ArtistEntity> getArtistByKeyword(String keyword) {
        return artistRepository.findByNameContaining(keyword);
    }
}
