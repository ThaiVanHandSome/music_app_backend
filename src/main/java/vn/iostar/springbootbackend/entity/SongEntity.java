package vn.iostar.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
public class SongEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_song")
    private Long idSong;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(1000)")
    private String name;

    @Column(name = "views")
    private int views;

    @Column(name = "resource", nullable = false, columnDefinition = "varchar(1000)")
    private String resource;

    @Column(name = "image", nullable = false, columnDefinition = "varchar(1000)")
    private String image;

    @JsonIgnoreProperties("songs")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_album", referencedColumnName = "id_album")
    private AlbumEntity album;

    @OneToMany(mappedBy = "song")
    private List<PlaylistSongEntity> playlistSongs;

    @OneToMany(mappedBy = "song")
    private List<SongLikedEntity> songLikeds;

    @OneToMany(mappedBy = "song")
    private List<ArtistSongEntity> artistSongs;

    @OneToMany(mappedBy = "song")
    private List<SongCommentEntity> songComments;

    public SongEntity(Long idSong) {
        this.idSong = idSong;
    }
}
