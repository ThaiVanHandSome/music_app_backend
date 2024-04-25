package vn.iostar.springbootbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.embededId.PlaylistSongId;
import vn.iostar.springbootbackend.entity.*;
import vn.iostar.springbootbackend.repository.PlaylistRepository;
import vn.iostar.springbootbackend.repository.PlaylistSongRepository;
import vn.iostar.springbootbackend.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Playlist> getPlaylistByUser(User user) {
        return playlistRepository.findByUser(user);
    }

    public List<Playlist> getPlaylistsByIdUser(Long idUser) {
        return playlistRepository.getPlaylistsByIdUser(idUser);
    }

    public Playlist createPlaylist(PlaylistRequest requestBody) {
        // Check user exists
        User user = userRepository.findById(requestBody.getIdUser()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        //Create playlist first
        Playlist savedPlaylist = new Playlist();
        savedPlaylist.setUser(user);
        savedPlaylist.setName(requestBody.getName());
        savedPlaylist.setImage(requestBody.getImage());
        savedPlaylist.setDayCreated(LocalDateTime.now());
        savedPlaylist.setPlaylistSongs(null);
        playlistRepository.save(savedPlaylist);

        //Add songs to playlist if songIds is not null
        List<Long> songIds = requestBody.getSongIds();
        if (songIds != null) {
            List<PlaylistSong> playlistSongs = new ArrayList<>();
            for (Long idSong: songIds) {
                PlaylistSong playlistSong = new PlaylistSong();
                playlistSong.setPlaylistSongId(new PlaylistSongId(savedPlaylist.getIdPlaylist(), idSong));
                playlistSong.setDayAdded(savedPlaylist.getDayCreated());
                playlistSongs.add(playlistSongRepository.save(playlistSong));
            }
            savedPlaylist.setPlaylistSongs(playlistSongs);
        }
        return playlistRepository.save(savedPlaylist);
    }

    public void deletePlaylist(Long idPlaylist) {
        Playlist playlist = playlistRepository.findById(idPlaylist).orElseThrow(
                () -> new RuntimeException("Playlist not found")
        );
        playlist.getPlaylistSongs().clear();
        playlistRepository.deleteById(playlist.getIdPlaylist());
    }
}
