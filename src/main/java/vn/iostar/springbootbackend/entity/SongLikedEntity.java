package vn.iostar.springbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.embededId.SongLikedId;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song_liked")
public class SongLikedEntity implements Serializable {

    @EmbeddedId
    private SongLikedId songLikedId;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false,  updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_song", referencedColumnName = "id_song", insertable = false,  updatable = false)
    private SongEntity song;
}
