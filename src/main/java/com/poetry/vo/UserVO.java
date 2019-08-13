package com.poetry.vo;

import lombok.Data;

@Data
public class UserVO {
    private String userId;
    private String username;
    private String nickname;
    private String createTime;
    private String updateTime;
    private String token;
}
