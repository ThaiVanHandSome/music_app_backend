package vn.iostar.springbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.embededId.ArtistSongId;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artist_songs")
public class ArtistSongEntity implements Serializable {

    @EmbeddedId
    private ArtistSongId artistSongId;

    @ManyToOne
    @JoinColumn(name = "id_artist", referencedColumnName = "id_artist", insertable = false, updatable = false)
    private ArtistEntity artist;

    @ManyToOne
    @JoinColumn(name = "id_song", referencedColumnName = "id_song", insertable = false, updatable = false)
    private SongEntity song;
}
