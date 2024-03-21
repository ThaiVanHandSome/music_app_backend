package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.AlbumEntity;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
    @Query("select a from AlbumEntity a where a.artist.idArtist = :idArtist")
    List<AlbumEntity> getAlbumByIdArtist(@Param("idArtist") Long idArtist);

    List<AlbumEntity> findByNameContaining(String name);
}
