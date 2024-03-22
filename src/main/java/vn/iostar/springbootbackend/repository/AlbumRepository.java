package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.AlbumEntity;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
}
