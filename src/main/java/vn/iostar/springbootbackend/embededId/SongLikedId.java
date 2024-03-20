package vn.iostar.springbootbackend.embededId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SongLikedId implements Serializable {

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_song")
    private Long idSong;
}
