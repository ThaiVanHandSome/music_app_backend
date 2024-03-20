package vn.iostar.springbootbackend.embededId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ArtistSongId implements Serializable {

    @Column(name = "id_artist")
    private Long idArtist;

    @Column(name = "id_song")
    private Long idSong;
}
