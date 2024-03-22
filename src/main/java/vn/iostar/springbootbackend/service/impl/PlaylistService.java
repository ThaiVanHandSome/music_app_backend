package vn.iostar.springbootbackend.service.impl;

import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.PlaylistEntity;
import vn.iostar.springbootbackend.entity.UserEntity;
import vn.iostar.springbootbackend.repository.PlaylistRepository;

import java.util.List;

@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<PlaylistEntity> getPlaylistByUser(UserEntity user) {
        return playlistRepository.findByUser(user);
    }
}
