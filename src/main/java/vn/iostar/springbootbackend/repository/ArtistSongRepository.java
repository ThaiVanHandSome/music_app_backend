package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.embededId.ArtistSongId;
import vn.iostar.springbootbackend.entity.ArtistEntity;
import vn.iostar.springbootbackend.entity.ArtistSongEntity;
import vn.iostar.springbootbackend.entity.SongEntity;

import java.util.List;
import java.util.Optional;
@Repository
public interface ArtistSongRepository extends JpaRepository<ArtistSongEntity, ArtistSongId> {

    @Query("SELECT s.song FROM ArtistSongEntity s WHERE s.artistSongId.idArtist = ?1")
    List<SongEntity> findAllSongsByArtistId(Long id_artist);

    @Query("SELECT a.artist FROM ArtistSongEntity a WHERE a.artistSongId.idSong = ?1")
    Optional<ArtistEntity> findArtistBySongId(Long id_song);

}
