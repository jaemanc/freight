package com.express.freight.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    private String name;

    private String contact;

    private String email;

    private String extra;

    @JsonFormat(pattern = "yyyy:MM:dd", timezone = "Asia/Seoul")
    private LocalDate createdAt;

    @Builder
    @QueryProjection
    public UserDto(String userId, String name, String contact, String email, String extra, LocalDate createdAt) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.extra = extra;
        this.createdAt = createdAt;
    }



}
