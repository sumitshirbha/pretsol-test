package com.pretsol.demo.entity;

import com.pretsol.demo.controller.form.CommentForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "By", nullable = false)
    private String by;
    @Column(name = "Text", columnDefinition = "TEXT", nullable = false)
    private String text;
    @Column(name = "DateOfComment") //DATETIME does not exist in postgres sql therefore using timestampz
    private ZonedDateTime dateOfComment;

    public CommentEntity(CommentForm commentForm) {
        this.by = commentForm.getBy();
        this.text = commentForm.getText();
    }
}
