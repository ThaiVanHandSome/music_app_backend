package vn.iostar.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.embededId.CommentLikedId;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment_liked")
public class CommentLikedEntity implements Serializable {
    @EmbeddedId
    private CommentLikedId commentLikedId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_comment", referencedColumnName = "id_comment", insertable = false,  updatable = false)
    private SongCommentEntity songComment;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false,  updatable = false)
    private UserEntity user;

}
