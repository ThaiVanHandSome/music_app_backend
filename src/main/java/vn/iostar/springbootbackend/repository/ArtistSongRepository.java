package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.embededId.ArtistSongId;
import vn.iostar.springbootbackend.entity.Artist;
import vn.iostar.springbootbackend.entity.ArtistSong;
import vn.iostar.springbootbackend.entity.Song;

import java.util.List;

@Repository
public interface ArtistSongRepository extends JpaRepository<ArtistSong, ArtistSongId> {

    @Query("SELECT s.song FROM ArtistSong s WHERE s.artistSongId.idArtist = ?1")
    List<Song> findAllSongsByArtistId(Long id_artist);

    @Query("SELECT a.artist FROM ArtistSong a WHERE a.artistSongId.idSong = ?1")
    List<Artist> findArtistBySongId(Long id_song);

}