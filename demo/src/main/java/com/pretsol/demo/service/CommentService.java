package com.pretsol.demo.service;

import com.pretsol.demo.controller.form.CommentForm;
import com.pretsol.demo.entity.CommentEntity;
import com.pretsol.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity getComment(Long commentId) {
        Optional<CommentEntity> comment = commentRepository.findById(commentId);
        return comment.orElse(null);
    }

    public CommentEntity addComment(CommentForm comment) {
        CommentEntity entity = new CommentEntity(comment);
        entity.setDateOfComment(ZonedDateTime.now(ZoneId.systemDefault()));
        return commentRepository.save(entity);
    }

    public CommentEntity getCommentByUserName(String userName) {
        Optional<CommentEntity> comment = commentRepository.findByBy(userName);
        return comment.orElse(null);
    }

    public List<CommentEntity> getAllComments() {
        return commentRepository.findAll();
    }

    public List<CommentEntity> getAllCommentsByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        ZonedDateTime from = parsedDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime to = parsedDate.plusDays(1).atStartOfDay(ZoneId.systemDefault());
        return commentRepository.findAllWithCreationDateTimeBefore(from, to);
    }

    public CommentEntity updateComment(CommentForm commentForm) {
        Optional<CommentEntity> comment = commentRepository.findById(commentForm.getId());

        if (comment.isPresent()) {
            CommentEntity commentEntity = comment.get();
            Optional.ofNullable(commentForm.getBy()).ifPresent(commentEntity::setBy);// functionally should not be updated
            Optional.ofNullable(commentForm.getText()).ifPresent(commentEntity::setText);
            return commentRepository.saveAndFlush(commentEntity);
        } else {
            return null;
        }
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
