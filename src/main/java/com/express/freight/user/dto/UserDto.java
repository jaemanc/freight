package com.express.freight.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long user_id;

    private String name;

    private String contact;

    private String email;

    private String extra;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @QueryProjection
    public UserDto(Long user_id, String name, String contact, String email, String extra, Date createdAt) {
        this.user_id = user_id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.extra = extra;
        this.createdAt = createdAt;
    }
}
