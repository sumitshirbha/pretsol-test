package com.pretsol.demo.controller.form;

import lombok.Data;


@Data
public class CommentForm {
    private Long id;
    private String by;
    private String text;
}
