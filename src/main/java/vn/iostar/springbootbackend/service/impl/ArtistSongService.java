package vn.iostar.springbootbackend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.Artist;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.repository.ArtistSongRepository;

import java.util.List;

@Service
public class ArtistSongService {
    private final ArtistSongRepository artistSongRepository;
    @Autowired
    public ArtistSongService(ArtistSongRepository artistSongRepository) {
        this.artistSongRepository = artistSongRepository;
    }

    public List<Song> findAllSongsByArtistId(Long id_artist) {
        return artistSongRepository.findAllSongsByArtistId(id_artist);
    }

    public List<Artist> findArtistBySongId(Long id_song){
        return artistSongRepository.findArtistBySongId(id_song);
    }
}
