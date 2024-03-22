package vn.iostar.springbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artists")
public class ArtistEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artist")
    private Long idArtist;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(1000)")
    private String name;

    @Column(name = "birth_day")
    private LocalDateTime birthDay;

    @Column(name = "gender", nullable = false)
    private int gender;

    @Column(name = "introduction", columnDefinition = "nvarchar(1000)")
    private String introduction;

    @Column(name = "avatar", nullable = false, columnDefinition = "varchar(1000)")
    private String avatar;

    @OneToMany(mappedBy = "artist")
    private List<AlbumEntity> albums;

    @OneToMany(mappedBy = "artist")
    private List<ArtistSongEntity> artistSongs;
}
