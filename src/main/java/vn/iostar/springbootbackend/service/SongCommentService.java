package vn.iostar.springbootbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.SongComment;
import vn.iostar.springbootbackend.entity.Song;
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

    public List<SongComment> findAllComentsBySong(Optional<Song> song){
        return songCommentRepository.findAllCommentsBySong(song);
    }

    public SongComment saveComment(SongComment comment) {
        return songCommentRepository.save(comment);
    }


    public Optional<SongComment> findCommentByIdComment(Long id_comment){
        return songCommentRepository.findCommentByIdComment(id_comment);
    }
}
