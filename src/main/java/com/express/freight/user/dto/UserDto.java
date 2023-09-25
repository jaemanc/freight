package com.express.freight.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserDto {

    @ApiModelProperty(example = "2001user001", dataType = "string")
    private String userId;

    @ApiModelProperty(example = "김준영의 발톱을 먹고 태어난 시골쥐", dataType = "string")
    private String name;

    @ApiModelProperty(example = "010-4157-8829", dataType = "string")
    private String contact;

    @ApiModelProperty(example = "asdf@gmail.com", dataType = "string")
    private String email;

    @ApiModelProperty(example = "기타 특이 사항 : 피부가 많이 까맣다.", dataType = "string")
    private String extra;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "2021-01-11", type = "string")
    @ApiModelProperty(example = "2021-01-11",dataType = "string")
    private Date createdAt;

    @Builder
    @QueryProjection
    public UserDto(String userId, String name, String contact, String email, String extra, Date createdAt) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.extra = extra;
        this.createdAt = createdAt;
    }



}
