package vn.iostar.springbootbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.embededId.CommentLikedId;
import vn.iostar.springbootbackend.embededId.SongLikedId;
import vn.iostar.springbootbackend.entity.CommentLikedEntity;
import vn.iostar.springbootbackend.entity.SongLikedEntity;
import vn.iostar.springbootbackend.repository.CommentLikedRepository;

import javax.transaction.Transactional;

@Service
public class CommentLikedService {
    private final CommentLikedRepository commentLikedRepository;
    @Autowired
    public CommentLikedService(CommentLikedRepository commentLikedRepository) {
        this.commentLikedRepository = commentLikedRepository;
    }

    @Query("SELECT COUNT(likes) FROM CommentLikedEntity likes WHERE likes.commentLikedId.idComment = ?1 AND likes.commentLikedId.idUser = ?2")
    public Long countLikesByCommentIdAndUserId(Long id_comment, Long id_user){
        return commentLikedRepository.countLikesByCommentIdAndUserId(id_comment, id_user);
    }

    public boolean isUserLikedComment(Long id_comment, Long id_user) {
        return commentLikedRepository.isUserLikedComment(id_comment, id_user);
    }

    public <S extends CommentLikedEntity> S save(S entity) {
        return commentLikedRepository.save(entity);
    }

    @Transactional
    public void toggleLike(Long id_comment, Long id_user) {
        if (isUserLikedComment(id_comment, id_user)) {
            commentLikedRepository.deleteByCommentLikedId(new CommentLikedId(id_comment, id_user));
            commentLikedRepository.decreaseViewCount(id_comment);
        } else {
            CommentLikedEntity commentLikedEntity = new CommentLikedEntity();
            commentLikedEntity.setCommentLikedId(new CommentLikedId(id_comment, id_user));
            commentLikedRepository.save(commentLikedEntity);
            commentLikedRepository.increaseLikesCount(id_comment);

        }
    }
}
