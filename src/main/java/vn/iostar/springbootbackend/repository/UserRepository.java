package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @Query("SELECT u FROM UserEntity u WHERE u.idUser = ?1")
    Optional<UserEntity> findByIdUser(Long id_user);
    public Optional<UserEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity a " +
            "SET a.isActive = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}
