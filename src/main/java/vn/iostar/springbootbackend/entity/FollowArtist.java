package vn.iostar.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.embededId.FollowArtistId;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "follow_artists")
public class FollowArtist {
    @EmbeddedId
    private FollowArtistId followArtistId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_artist", referencedColumnName = "id_artist", insertable = false, updatable = false)
    private Artist artist;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private User user;
}
