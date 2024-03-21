package vn.iostar.springbootbackend.service;

import vn.iostar.springbootbackend.entity.PlaylistSongEntity;
import vn.iostar.springbootbackend.entity.SongEntity;

import java.util.List;

public interface IPlaylistSongService {
    List<SongEntity> findAllByPlaylistSongId(Long id_playlist);

    int deleteByPlaylistSongId(Long id_playlist, Long id_song);

    List<PlaylistSongEntity> findAll();

    List<PlaylistSongEntity> findAllById(Iterable<Long> longs);

    <S extends PlaylistSongEntity> S save(S entity);

    void delete(PlaylistSongEntity entity);

    void deleteAllById(Iterable<? extends Long> longs);
}
