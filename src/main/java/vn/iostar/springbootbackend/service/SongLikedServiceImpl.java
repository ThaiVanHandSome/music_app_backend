package vn.iostar.springbootbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.embededId.SongLikedId;
import vn.iostar.springbootbackend.entity.SongLikedEntity;
import vn.iostar.springbootbackend.repository.SongLikedRepository;

import javax.transaction.Transactional;

@Service
public class SongLikedServiceImpl implements ISongLikedService {

    private final SongLikedRepository songLikedRepository;

    @Autowired
    public SongLikedServiceImpl(SongLikedRepository songLikedRepository) {
        this.songLikedRepository = songLikedRepository;
    }

    @Override
    @Query("SELECT COUNT(s) FROM SongLikedEntity s WHERE s.songLikedId.id_song = ?1")
    public Long countLikesBySongId(Long songId) {
        return songLikedRepository.countLikesBySongId(songId);
    }

    @Override
    @Query("SELECT COUNT(s) FROM SongLikedEntity s WHERE s.songLikedId.id_song = ?1 AND s.songLikedId.id_user = ?2")
    public Long countLikesBySongIdAndUserId(Long songId, Long userId) {
        return songLikedRepository.countLikesBySongIdAndUserId(songId, userId);
    }

    @Override
    public boolean isUserLikedSong(Long songId, Long userId) {
        return songLikedRepository.isUserLikedSong(songId, userId);
    }

    @Override
    public <S extends SongLikedEntity> S save(S entity) {
        return songLikedRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        songLikedRepository.deleteById(aLong);
    }

    @Override
    public void delete(SongLikedEntity entity) {
        songLikedRepository.delete(entity);
    }

    @Override
    public void deleteBySongLikedId(SongLikedId songLikedId) {
        songLikedRepository.deleteBySongLikedId(songLikedId);
    }

    @Override
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
