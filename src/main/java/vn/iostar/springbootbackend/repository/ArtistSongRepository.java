package vn.iostar.springbootbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.embededId.ArtistSongId;
import vn.iostar.springbootbackend.entity.ArtistSong;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.entity.User;

import java.util.List;

@Repository
public interface ArtistSongRepository extends JpaRepository<ArtistSong, ArtistSongId> {

    @Query("SELECT s.song FROM ArtistSong s WHERE s.artistSongId.idArtist = ?1")
    Page<Song> findAllSongsByArtistId(Long id_artist, Pageable pageable);

    @Query("SELECT a.artist FROM ArtistSong a WHERE a.artistSongId.idSong = ?1")
    List<User> findArtistBySongId(Long id_song);

    @Query("SELECT a.song FROM ArtistSong a WHERE a.artist.idUser = :artistId ORDER BY a.song.views DESC")
    List<Song> findSongsByArtistIdOrderByViewCountDesc(Long artistId);

    @Query("SELECT COUNT(s.song) FROM ArtistSong s WHERE s.artistSongId.idArtist = ?1")
    int countSongsByArtistId(Long id_artist);

    @Query("SELECT SUM(s.views) FROM ArtistSong ast JOIN ast.song s WHERE ast.artist.idUser = :artistId")
    int countTotalViewsByArtistId(Long artistId);

    @Query("SELECT COUNT(fa) FROM FollowArtist fa WHERE fa.artist.id = :artistId")
    int countUsersByArtistId(Long artistId);

    @Query("SELECT COUNT(sc) FROM ArtistSong ast JOIN ast.song s JOIN s.songComments sc WHERE ast.artist.idUser = :artistId")
    int countCommentsByArtistId(Long artistId);
}
