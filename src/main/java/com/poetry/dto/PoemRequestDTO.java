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
public class PoemRequestDTO implements Serializable {
    private String userId;
    private String contentId;
    private String writer;
    private String keyword;
    private Boolean isCollect;
}
