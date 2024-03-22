package vn.iostar.springbootbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.PlaylistSongEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.repository.PlaylistSongRepository;

import java.util.List;

@Service
public class PlaylistSongService {

    private final PlaylistSongRepository playlistSongRepository;

    @Autowired
    public PlaylistSongService(PlaylistSongRepository playlistSongRepository) {
        this.playlistSongRepository = playlistSongRepository;
    }

    public List<SongEntity> findAllByPlaylistSongId(Long id_playlist) {
        return playlistSongRepository.findAllByPlaylistSongId(id_playlist);
    }

    public int deleteByPlaylistSongId(Long id_playlist, Long id_song) {
        return playlistSongRepository.deleteByPlaylistSongId(id_playlist, id_song);
    }

    public List<PlaylistSongEntity> findAll() {
        return playlistSongRepository.findAll();
    }

    public List<PlaylistSongEntity> findAllById(Iterable<Long> longs) {
        return playlistSongRepository.findAllById(longs);
    }

    public <S extends PlaylistSongEntity> S save(S entity) {
        return playlistSongRepository.save(entity);
    }

    public void delete(PlaylistSongEntity entity) {
        playlistSongRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> longs) {
        playlistSongRepository.deleteAllById(longs);
    }
}
