package com.express.freight.refuel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RefuelDto {
    private Long id;

    private Long userId;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date refuelingDate;

    private Long price;

    private String extra;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @QueryProjection
    public RefuelDto(Long id, Long userId, Date refuelingDate, Long price, String extra, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.refuelingDate = refuelingDate;
        this.price = price;
        this.extra = extra;
        this.createdAt = createdAt;
    }
}
