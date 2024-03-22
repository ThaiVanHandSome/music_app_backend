package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.PlaylistEntity;
import vn.iostar.springbootbackend.entity.UserEntity;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {

    List<PlaylistEntity> findByUser(UserEntity user);
}
