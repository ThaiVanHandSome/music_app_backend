package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findByUuid(String uuid);
    @Query("SELECT u FROM UserEntity u WHERE u.id_user = ?1")
    Optional<UserEntity> findByIdUser(Long id_user);
    public Optional<UserEntity> findByEmail(String email);
}
