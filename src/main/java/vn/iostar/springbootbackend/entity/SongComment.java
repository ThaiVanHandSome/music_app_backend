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
public class SongComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long idComment;

    @Column(name = "content", nullable = false, columnDefinition = "nvarchar(1000)")
    private String content;

    @Column(name = "likes", nullable = false)
    private int likes;

    @Column(name = "day_commented")
    private LocalDateTime dayCommented;

    @OneToMany(mappedBy = "songComment")
    private List<CommentLiked> commentLikeds;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_song", referencedColumnName = "id_song")
    private Song song;

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public LocalDateTime getDayCommented() {
        return dayCommented;
    }

    public void setDayCommented(LocalDateTime dayCommented) {
        this.dayCommented = dayCommented;
    }

    public List<CommentLiked> getCommentLikeds() {
        return commentLikeds;
    }

    public void setCommentLikeds(List<CommentLiked> commentLikeds) {
        this.commentLikeds = commentLikeds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
