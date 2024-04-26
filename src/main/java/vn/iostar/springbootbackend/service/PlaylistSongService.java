package vn.iostar.springbootbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.PlaylistSong;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.repository.PlaylistSongRepository;

import java.util.List;

@Service
public class PlaylistSongService {

    private final PlaylistSongRepository playlistSongRepository;

    @Autowired
    public PlaylistSongService(PlaylistSongRepository playlistSongRepository) {
        this.playlistSongRepository = playlistSongRepository;
    }

    public List<Song> findAllByPlaylistSongId(Long id_playlist) {
        return playlistSongRepository.findAllByPlaylistSongId(id_playlist);
    }

    public int deleteByPlaylistSongId(Long id_playlist, Long id_song) {
        return playlistSongRepository.deleteByPlaylistSongId(id_playlist, id_song);
    }

    public List<PlaylistSong> findAll() {
        return playlistSongRepository.findAll();
    }

    public List<PlaylistSong> findAllById(Iterable<Long> longs) {
        return playlistSongRepository.findAllById(longs);
    }

    public <S extends PlaylistSong> S save(S entity) {
        return playlistSongRepository.save(entity);
    }

    public void delete(PlaylistSong entity) {
        playlistSongRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> longs) {
        playlistSongRepository.deleteAllById(longs);
    }
}
