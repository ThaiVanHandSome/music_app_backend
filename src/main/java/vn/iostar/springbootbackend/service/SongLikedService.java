package vn.iostar.springbootbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.embededId.SongLikedId;
import vn.iostar.springbootbackend.entity.Song;
import vn.iostar.springbootbackend.entity.SongLiked;
import vn.iostar.springbootbackend.model.SongModel;
import vn.iostar.springbootbackend.repository.SongLikedRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SongLikedService {

    @Autowired
    private SongLikedRepository songLikedRepository;

    @Autowired
    private SongService songService;

    @Autowired
    public SongLikedService(SongLikedRepository songLikedRepository) {
        this.songLikedRepository = songLikedRepository;
    }

    @Query("SELECT COUNT(s) FROM SongLiked s WHERE s.songLikedId.idSong = ?1")
    public Long countLikesBySongId(Long songId) {
        return songLikedRepository.countLikesBySongId(songId);
    }

    @Query("SELECT COUNT(s) FROM SongLiked s WHERE s.songLikedId.idSong = ?1 AND s.songLikedId.idUser = ?2")
    public Long countLikesBySongIdAndUserId(Long songId, Long userId) {
        return songLikedRepository.countLikesBySongIdAndUserId(songId, userId);
    }

    public boolean isUserLikedSong(Long songId, Long userId) {
        return songLikedRepository.isUserLikedSong(songId, userId);
    }

    public <S extends SongLiked> S save(S entity) {
        return songLikedRepository.save(entity);
    }

    public void deleteById(Long aLong) {
        songLikedRepository.deleteById(aLong);
    }

    public void delete(SongLiked entity) {
        songLikedRepository.delete(entity);
    }

    public void deleteBySongLikedId(SongLikedId songLikedId) {
        songLikedRepository.deleteBySongLikedId(songLikedId);
    }

    @Transactional
    public boolean toggleLike(Long songId, Long userId) {
        if (isUserLikedSong(songId, userId)) {
            songLikedRepository.deleteBySongLikedId(new SongLikedId(userId, songId));
            return false;
        } else {
            SongLiked songLiked = new SongLiked();
            songLiked.setSongLikedId(new SongLikedId(userId, songId));
            songLiked.setDayLiked(LocalDateTime.now());
            songLikedRepository.save(songLiked);
            return true;
        }
    }

    public List<SongModel> getLikedSongsByIdUser(Long idUser) {
        List<Song> songs = songLikedRepository.getLikedSongsByIdUser(idUser);
        return songService.convertToSongModel(songs);
    }

    public List<SongModel> getNotLikedSongsByIdUser(Long idUser) {
        List<Song> songs = songLikedRepository.getNotLikedSongsByIdUser(idUser);
        return songService.convertToSongModel(songs);
    }
}
