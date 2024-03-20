package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.SongEntity;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {
}
