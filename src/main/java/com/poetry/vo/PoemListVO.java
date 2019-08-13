package com.poetry.vo;

import lombok.Data;

@Data
public class PoemListVO {
    private String contentId;
    private Integer contentIndex;
    private String writer;
    private String dynasty;
    private String title;
}