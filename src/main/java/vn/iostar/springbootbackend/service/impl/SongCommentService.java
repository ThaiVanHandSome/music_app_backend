package vn.iostar.springbootbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.SongCommentEntity;
import vn.iostar.springbootbackend.entity.SongEntity;
import vn.iostar.springbootbackend.repository.SongCommentRepository;

import java.util.List;
import java.util.Optional;


@Service
public class SongCommentService {
    private final SongCommentRepository songCommentRepository;
    @Autowired
    public SongCommentService(SongCommentRepository songCommentRepository) {
        this.songCommentRepository = songCommentRepository;
    }

    public List<SongCommentEntity> findAllComentsBySong(Optional<SongEntity> song){
        return songCommentRepository.findAllCommentsBySong(song);
    }

    public SongCommentEntity saveComment(SongCommentEntity comment) {
        return songCommentRepository.save(comment);
    }


    public Optional<SongCommentEntity> findCommentByIdComment(Long id_comment){
        return songCommentRepository.findCommentByIdComment(id_comment);
    }
}
