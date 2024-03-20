package vn.iostar.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "song_comments")
public class SongCommentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comment;

    @Column(name = "content", nullable = false, columnDefinition = "nvarchar(1000)")
    private String content;

    @Column(name = "likes", nullable = false)
    private int likes;

    @Column(name = "day_commented")
    private LocalDateTime day_commented;

    @OneToMany(mappedBy = "songComment")
    private List<CommentLikedEntity> commentLikeds;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_song", referencedColumnName = "id_song")
    private SongEntity song;
}
