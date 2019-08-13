package com.poetry.entity;

import lombok.Data;

@Data
public class PoemDO {
    private String contentId;
    private Integer contentIndex;
    private String writer;
    private String dynasty;
    private String content;
    private String title;
}
