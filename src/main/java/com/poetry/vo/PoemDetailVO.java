package com.poetry.vo;

import lombok.Data;

import java.util.List;

@Data
public class PoemDetailVO {
    private String contentId;
    private Integer contentIndex;
    private String writer;
    private String dynasty;
    private String content;
    private String title;

    private List<LabelVO> labelList;
}
