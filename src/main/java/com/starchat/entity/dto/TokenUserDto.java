package com.starchat.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenUserDto implements Serializable {
    private String userId;
    private String token;
    private String nickname;
    private Boolean admin;
}
