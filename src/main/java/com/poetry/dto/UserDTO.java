package com.poetry.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String createTime;
    private String updateTime;
    private String token;
}
