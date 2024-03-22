package vn.iostar.springbootbackend.embededId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PlaylistSongId implements Serializable {
    @Column(name = "id_playlist")
    private Long idPlaylist;

    @Column(name = "id_song")
    private Long idSong;
}
