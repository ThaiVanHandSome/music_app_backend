package vn.iostar.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.embededId.SongLikedId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song_liked")
public class SongLiked implements Serializable {

    @EmbeddedId
    private SongLikedId songLikedId;

    @Column(name = "day_liked")
    private LocalDateTime dayLiked;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false,  updatable = false)
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_song", referencedColumnName = "id_song", insertable = false,  updatable = false)
    private Song song;
}
