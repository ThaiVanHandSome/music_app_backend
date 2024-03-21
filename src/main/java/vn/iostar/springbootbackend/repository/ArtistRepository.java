package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.ArtistEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    List<ArtistEntity> findByNameContaining(String name);
}
