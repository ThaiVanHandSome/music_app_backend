package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.PlaylistSongEntity;
import vn.iostar.springbootbackend.entity.SongEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSongEntity, Long> {
    @Query("SELECT s.song FROM PlaylistSongEntity s WHERE s.playlistSongId.id_playlist = ?1")
    List<SongEntity> findAllByPlaylistSongId(Long id_playlist);

    @Modifying
    @Transactional
    @Query("DELETE FROM PlaylistSongEntity s WHERE s.playlistSongId.id_playlist = ?1 AND s.playlistSongId.id_song = ?2")
    int deleteByPlaylistSongId(Long id_playlist, Long id_song);

}
