package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.iostar.springbootbackend.entity.AlbumEntity;
import vn.iostar.springbootbackend.entity.SongEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {
    List<SongEntity> findByViewsGreaterThan(int views);

    List<SongEntity> findByAlbum(AlbumEntity album);

    List<SongEntity> findByNameContaining(String keyword);

    Optional<SongEntity> findById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE SongEntity s SET s.views = s.views + 1 WHERE s.idSong = :songId")
    void incrementViewCount(Long songId);
}
