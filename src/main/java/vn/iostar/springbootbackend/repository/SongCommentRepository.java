package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.embededId.SongLikedId;
import vn.iostar.springbootbackend.entity.SongCommentEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.entity.UserEntity;


import java.util.List;
import java.util.Optional;

@Repository
public interface SongCommentRepository extends JpaRepository<SongCommentEntity, Long> {

    List<SongCommentEntity> findAllCommentsBySong(Optional<SongEntity> song);

    List<SongCommentEntity> findAllComentsByUser(Optional<UserEntity> user);


    @Override
    <S extends SongCommentEntity> S save(S entity);

    Optional<SongCommentEntity> findCommentByIdComment(Long id_comment);



}
