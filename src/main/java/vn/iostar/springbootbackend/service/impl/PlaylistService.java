package vn.iostar.springbootbackend.service.impl;

import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.Playlist;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.repository.PlaylistRepository;

import java.util.List;

@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> getPlaylistByUser(User user) {
        return playlistRepository.findByUser(user);
    }
}
