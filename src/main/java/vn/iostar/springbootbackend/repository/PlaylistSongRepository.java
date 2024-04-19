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
    @Query("SELECT s.song FROM PlaylistSong s WHERE s.playlistSongId.idPlaylist = ?1")
    List<Song> findAllByPlaylistSongId(Long id_playlist);

    @Modifying
    @Transactional
    @Query("DELETE FROM PlaylistSong s WHERE s.playlistSongId.idPlaylist = ?1 AND s.playlistSongId.idSong = ?2")
    int deleteByPlaylistSongId(Long id_playlist, Long id_song);

}
