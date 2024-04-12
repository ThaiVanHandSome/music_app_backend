package vn.iostar.springbootbackend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.ArtistEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.repository.ArtistSongRepository;
import vn.iostar.springbootbackend.repository.PlaylistSongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistSongService {
    private final ArtistSongRepository artistSongRepository;
    @Autowired
    public ArtistSongService(ArtistSongRepository artistSongRepository) {
        this.artistSongRepository = artistSongRepository;
    }

    public List<SongEntity> findAllSongsByArtistId(Long id_artist) {
        return artistSongRepository.findAllSongsByArtistId(id_artist);
    }

    public List<ArtistEntity> findArtistBySongId(Long id_song){
        return artistSongRepository.findArtistBySongId(id_song);
    }
}
