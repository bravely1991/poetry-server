package com.poetry.entity;

import lombok.Data;

@Data
public class UserPoemDO {
    private String userId;
    private String contentId;
    private Integer isCollected;
    private Integer isMemorized;
    private Integer isPreparing;
}
