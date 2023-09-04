package com.express.freight.operate.dto;

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
public class OperateDto {
    private Long id;

    private Long userId;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date loadingDate;

    private String loadingPlace;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date unloadingDate;

    private String unloadingPlace;

    private Long loadingRatio;

    private Long transportationCosts;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date transportationDate;

    private String transportationType;

    private Long unitCost;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @QueryProjection
    public OperateDto(Long id, Long userId, Date loadingDate, String loadingPlace, Date unloadingDate, String unloadingPlace, Long loadingRatio, Long transportationCosts, Date transportationDate, String transportationType, Long unitCost, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.loadingDate = loadingDate;
        this.loadingPlace = loadingPlace;
        this.unloadingDate = unloadingDate;
        this.unloadingPlace = unloadingPlace;
        this.loadingRatio = loadingRatio;
        this.transportationCosts = transportationCosts;
        this.transportationDate = transportationDate;
        this.transportationType = transportationType;
        this.unitCost = unitCost;
        this.createdAt = createdAt;
    }
}
