package com.poetry.dto;

import lombok.Data;

@Data
public class PoemDTO {
    private String contentId;
    private String contentIndex;
    private String title;
    private String writer;
    private String dynasty;
    private String content;

    public static class UserPoemDTO {

    }
}
