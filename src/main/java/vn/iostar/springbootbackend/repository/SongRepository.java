package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.iostar.springbootbackend.entity.Album;
import vn.iostar.springbootbackend.entity.Song;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByViewsGreaterThan(int views);

    List<Song> findByAlbum(Album album);

    List<Song> findByNameContaining(String keyword);

    Optional<Song> findById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Song s SET s.views = s.views + 1 WHERE s.idSong = :songId")
    void incrementViewCount(Long songId);
}
