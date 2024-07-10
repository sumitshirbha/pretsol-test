package com.pretsol.demo.service;

import com.pretsol.demo.entity.CommentEntity;
import com.pretsol.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity getComment(Long userId) {
        Optional<CommentEntity> comment = commentRepository.findById(userId);
        return comment.orElse(null);
    }

    public CommentEntity addComment(CommentEntity comment) {
        comment.setDateOfComment(ZonedDateTime.now());
        return commentRepository.save(comment);
    }

    public CommentEntity getCommentByUserName(String userName) {
        Optional<CommentEntity> comment = commentRepository.findByBy(userName);
        return comment.orElse(null);
    }

    public List<CommentEntity> getAllComments() {

        List<CommentEntity> comments = commentRepository.findAll();
        return comments;
    }

//    public List<CommentEntity> getAllCommentsByDate() {
//    }
}
