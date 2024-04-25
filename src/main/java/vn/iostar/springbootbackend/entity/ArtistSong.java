package vn.iostar.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.embededId.ArtistSongId;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artist_songs")
public class ArtistSong implements Serializable {

    @Getter
    @EmbeddedId
    private ArtistSongId artistSongId;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_artist", referencedColumnName = "id_artist", insertable = false, updatable = false)
    private Artist artist;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_song", referencedColumnName = "id_song", insertable = false, updatable = false)
    private Song song;

    public void setArtistSongId(ArtistSongId artistSongId) {
        this.artistSongId = artistSongId;
    }
}
