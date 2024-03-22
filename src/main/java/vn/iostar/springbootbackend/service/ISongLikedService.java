package vn.iostar.springbootbackend.service;

import org.springframework.data.jpa.repository.Query;
import vn.iostar.springbootbackend.embededId.SongLikedId;
import vn.iostar.springbootbackend.entity.SongLikedEntity;

public interface ISongLikedService {

    @Query("SELECT COUNT(s) FROM SongLikedEntity s WHERE s.songLikedId.id_song = ?1")
    Long countLikesBySongId(Long songId);

    @Query("SELECT COUNT(s) FROM SongLikedEntity s WHERE s.songLikedId.id_song = ?1 AND s.songLikedId.id_user = ?2")
    Long countLikesBySongIdAndUserId(Long songId, Long userId);

    boolean isUserLikedSong(Long songId, Long userId);

    <S extends SongLikedEntity> S save(S entity);

    void deleteById(Long aLong);

    void delete(SongLikedEntity entity);

    void deleteBySongLikedId(SongLikedId songLikedId);

    void toggleLike(Long songId, Long userId);
}
