package vn.iostar.springbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "uuid", nullable = false, columnDefinition = "varchar(1000)")
    private String uuid;

    @Column(name = "first_name", nullable = false, columnDefinition = "nvarchar(1000)")
    private String first_name;

    @Column(name = "last_name", nullable = false, columnDefinition = "nvarchar(1000)")
    private String last_name;

    @Column(name = "email", nullable = false, columnDefinition = "varchar(1000)")
    private String email;

    @Column(name = "gender", nullable = false)
    private int gender;

    @Column(name = "avatar", columnDefinition = "varchar(1000)")
    private String avatar;

    @OneToOne(mappedBy = "user")
    private AccountEntity account;

    @OneToMany(mappedBy = "user")
    private List<CommentLikedEntity> commentLikeds;

    @OneToMany(mappedBy = "user")
    private List<PlaylistEntity> playlists;

    @OneToMany(mappedBy = "user")
    private List<SongCommentEntity> songComments;

    @OneToMany(mappedBy = "song")
    private List<SongLikedEntity> songLikeds;
}
