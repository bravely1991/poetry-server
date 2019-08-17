package com.poetry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PoemDTO implements Serializable {
    private String contentId;
    private String contentIndex;
    private String title;
    private String writer;
    private String dynasty;
    private String content;
}
