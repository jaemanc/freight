package com.express.freight.operate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OperateDto {
    private Long id;

    private String userId;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDate loadingDate;

    private String loadingPlace;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDate unloadingDate;

    private String unloadingPlace;

    private Long loadingRatio;

    private Long transportationCosts;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDate transportationDate;

    private String transportationType;

    private Long unitCost;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    private Character delYn;

    @QueryProjection
    public OperateDto(Long id, String userId, LocalDate loadingDate, String loadingPlace, LocalDate unloadingDate, String unloadingPlace, Long loadingRatio, Long transportationCosts, LocalDate transportationDate, String transportationType, Long unitCost, Date createdAt, Character delYn) {
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
        this.delYn = delYn;
    }
}
