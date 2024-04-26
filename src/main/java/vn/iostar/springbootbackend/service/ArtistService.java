package vn.iostar.springbootbackend.service;

import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.Artist;
import vn.iostar.springbootbackend.repository.ArtistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    public List<Artist> getArtistByKeyword(String keyword) {
        return artistRepository.findByNameContaining(keyword);
    }

    public void deleteArtist(Artist artist) {
        artistRepository.delete(artist);
    }
}
