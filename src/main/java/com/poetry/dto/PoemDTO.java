package com.poetry.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PoemDTO implements Serializable {
    private String contentId;
    private String contentIndex;
    private String title;
    private String writer;
    private String dynasty;
    private String content;

    public static class UserPoemDTO {

    }
}
