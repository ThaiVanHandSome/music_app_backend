package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iostar.springbootbackend.embededId.SongLikedId;
import vn.iostar.springbootbackend.entity.SongLikedEntity;

@Repository
public interface SongLikedRepository extends JpaRepository<SongLikedEntity, Long> {
    @Query("SELECT COUNT(s) FROM SongLikedEntity s WHERE s.songLikedId.idSong = ?1")
    Long countLikesBySongId(Long songId);

    @Query("SELECT COUNT(s) FROM SongLikedEntity s WHERE s.songLikedId.idSong = ?1 AND s.songLikedId.idUser = ?2")
    Long countLikesBySongIdAndUserId(Long songId, Long userId);

    default boolean isUserLikedSong(Long songId, Long userId) {
        return countLikesBySongIdAndUserId(songId, userId) > 0;
    }

    void deleteBySongLikedId(SongLikedId songLikedId);

}
