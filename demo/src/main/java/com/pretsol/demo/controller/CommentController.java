package com.pretsol.demo.controller;

import com.pretsol.demo.entity.CommentEntity;
import com.pretsol.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pretsol")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping(path = "/{userKey:^[0-9]*$}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentEntity> getComment(@PathVariable(required = true) Long userKey) {
        CommentEntity comment = commentService.getComment(userKey);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/username/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentEntity> getCommentByUserName(@PathVariable(required = true) String userName) {
        CommentEntity comment = commentService.getCommentByUserName(userName);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentEntity>> getAllComments() {
        List<CommentEntity> comments = commentService.getAllComments();
        if (!comments.isEmpty()) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<CommentEntity>> getAllCommentByDate() {
//        List<CommentEntity> comments = commentService.getAllCommentsByDate();
//        if (!comments.isEmpty()) {
//            return new ResponseEntity<>(comments, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentEntity> saveComment(@RequestBody(required = true) CommentEntity comment) {
        CommentEntity result = commentService.addComment(comment);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}