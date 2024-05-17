package vn.iostar.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongCommentModel {
    private Long idComment;
    private String content;
    private int likes;
    private User user;
}
