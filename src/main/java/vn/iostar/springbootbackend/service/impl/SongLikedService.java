package vn.iostar.springbootbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.embededId.SongLikedId;
import vn.iostar.springbootbackend.entity.SongLikedEntity;
import vn.iostar.springbootbackend.repository.SongLikedRepository;

import javax.transaction.Transactional;

@Service
public class SongLikedService {

    private final SongLikedRepository songLikedRepository;

    @Autowired
    public SongLikedService(SongLikedRepository songLikedRepository) {
        this.songLikedRepository = songLikedRepository;
    }

    @Query("SELECT COUNT(s) FROM SongLikedEntity s WHERE s.songLikedId.idSong = ?1")
    public Long countLikesBySongId(Long songId) {
        return songLikedRepository.countLikesBySongId(songId);
    }

    @Query("SELECT COUNT(s) FROM SongLikedEntity s WHERE s.songLikedId.idSong = ?1 AND s.songLikedId.idUser = ?2")
    public Long countLikesBySongIdAndUserId(Long songId, Long userId) {
        return songLikedRepository.countLikesBySongIdAndUserId(songId, userId);
    }

    public boolean isUserLikedSong(Long songId, Long userId) {
        return songLikedRepository.isUserLikedSong(songId, userId);
    }

    public <S extends SongLikedEntity> S save(S entity) {
        return songLikedRepository.save(entity);
    }

    public void deleteById(Long aLong) {
        songLikedRepository.deleteById(aLong);
    }

    public void delete(SongLikedEntity entity) {
        songLikedRepository.delete(entity);
    }

    public void deleteBySongLikedId(SongLikedId songLikedId) {
        songLikedRepository.deleteBySongLikedId(songLikedId);
    }

    @Transactional
    public void toggleLike(Long songId, Long userId) {
        if (isUserLikedSong(songId, userId)) {
            songLikedRepository.deleteBySongLikedId(new SongLikedId(songId, userId));
        } else {
            SongLikedEntity songLikedEntity = new SongLikedEntity();
            songLikedEntity.setSongLikedId(new SongLikedId(songId, userId));
            songLikedRepository.save(songLikedEntity);
        }
    }
}
