package vn.iostar.springbootbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.ArtistSong;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.repository.ArtistSongRepository;

import java.util.List;

@Service
public class ArtistSongService {
    private final ArtistSongRepository artistSongRepository;
    @Autowired
    public ArtistSongService(ArtistSongRepository artistSongRepository) {
        this.artistSongRepository = artistSongRepository;
    }

    public Page<Song> findAllSongsByArtistId(Long id_artist, Pageable pageable) {
        return artistSongRepository.findAllSongsByArtistId(id_artist, pageable);
    }

    public List<User> findArtistBySongId(Long idSong){
        return artistSongRepository.findArtistBySongId(idSong);
    }

    public Page<Song> getSongsOfArtistDesc(Long idArtist, Pageable pageable) {
        return artistSongRepository.findSongsByArtistIdOrderByViewCountDesc(idArtist, pageable);
    }

    public int countSongsByArtistId(Long idArtist) {
        return artistSongRepository.countSongsByArtistId(idArtist);
    }

    public void save(ArtistSong artistSong) {
        artistSongRepository.save(artistSong);
    }

    public int countTotalViewsByArtistId(Long idArtist) {
        return artistSongRepository.countTotalViewsByArtistId(idArtist);
    }

    public int countUsersByArtistId(Long idArtist) {
        return artistSongRepository.countUsersByArtistId(idArtist);
    }

    public int countCommentsByArtistId(Long idArtist) {
        return artistSongRepository.countCommentsByArtistId(idArtist);
    }
}
