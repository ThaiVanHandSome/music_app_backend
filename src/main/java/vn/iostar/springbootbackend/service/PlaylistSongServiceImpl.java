package vn.iostar.springbootbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.PlaylistSongEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.repository.PlaylistSongRepository;

import java.util.List;

@Service
public class PlaylistSongServiceImpl implements IPlaylistSongService {

    private final PlaylistSongRepository playlistSongRepository;

    @Autowired
    public PlaylistSongServiceImpl(PlaylistSongRepository playlistSongRepository) {
        this.playlistSongRepository = playlistSongRepository;
    }

    @Override
    public List<SongEntity> findAllByPlaylistSongId(Long id_playlist) {
        return playlistSongRepository.findAllByPlaylistSongId(id_playlist);
    }

    @Override
    public int deleteByPlaylistSongId(Long id_playlist, Long id_song) {
        return playlistSongRepository.deleteByPlaylistSongId(id_playlist, id_song);
    }

    @Override
    public List<PlaylistSongEntity> findAll() {
        return playlistSongRepository.findAll();
    }

    @Override
    public List<PlaylistSongEntity> findAllById(Iterable<Long> longs) {
        return playlistSongRepository.findAllById(longs);
    }

    @Override
    public <S extends PlaylistSongEntity> S save(S entity) {
        return playlistSongRepository.save(entity);
    }

    @Override
    public void delete(PlaylistSongEntity entity) {
        playlistSongRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        playlistSongRepository.deleteAllById(longs);
    }
}
