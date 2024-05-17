package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.PlaylistSong;
import vn.iostar.springbootbackend.entity.Song;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
    @Query("SELECT s.song FROM PlaylistSong s WHERE s.playlistSongId.idPlaylist = ?1 ORDER BY s.dayAdded ASC")
    List<Song> findAllByPlaylistSongId(Long id_playlist);

    @Modifying
    @Transactional
    @Query("DELETE FROM PlaylistSong s WHERE s.playlistSongId.idPlaylist = ?1 AND s.playlistSongId.idSong = ?2")
    int deleteByPlaylistSongId(Long id_playlist, Long id_song);

    @Query("SELECT COUNT(ps) FROM PlaylistSong ps WHERE ps.playlistSongId.idPlaylist = ?1 AND ps.playlistSongId.idSong = ?2")
    int count(Long id_playlist, Long idSong);

    default boolean isSongExistsInPlaylist(Long id_playlist, Long idSong) {
        return count(id_playlist, idSong) > 0;
    }
}
