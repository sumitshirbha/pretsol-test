package com.pretsol.demo.controller.dto;

import lombok.Data;


@Data
public class CommentDto {
    private Long id;
    private String by;
    private String text;
}
