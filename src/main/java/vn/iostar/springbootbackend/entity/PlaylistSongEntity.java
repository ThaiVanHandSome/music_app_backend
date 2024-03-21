package vn.iostar.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.embededId.PlaylistSongId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlist_songs")
public class PlaylistSongEntity implements Serializable {

    @EmbeddedId
    private PlaylistSongId playlistSongId;

    @Column(name = "day_added")
    private LocalDateTime day_added;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_playlist", referencedColumnName = "id_playlist", insertable = false,  updatable = false)
    private PlaylistEntity playlist;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_song", referencedColumnName = "id_song", insertable = false,  updatable = false)
    private SongEntity song;
}
