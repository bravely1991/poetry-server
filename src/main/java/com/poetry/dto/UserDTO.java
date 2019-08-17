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
public class UserDTO implements Serializable {
    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String createTime;
    private String updateTime;
    private String token;
    private String userIndex;

}
