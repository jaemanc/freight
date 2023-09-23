package com.express.freight.operate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OperateDto {

    private Long id;

    @ApiModelProperty(example = "2001user001", dataType = "string")
    private String userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2023-01-01", dataType = "string")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2021-01-11", type = "string")
    private LocalDate loadingDate;

    @Schema(example = "익산", type = "string")
    @ApiModelProperty(example = "익산", dataType = "string")
    private String loadingPlace;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2023-01-01", dataType = "string")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2021-01-11" , type = "string")
    private LocalDate unloadingDate;

    @Schema(example = "서울" , type = "string")
    @ApiModelProperty(example = "익산", dataType = "string")
    private String unloadingPlace;

    @Schema(example = "60", type = "string")
    @ApiModelProperty(example = "60", dataType = "string")
    private Long loadingRatio;

    @Schema(example = "39800", type = "string")
    @ApiModelProperty(example = "39800", dataType = "string")
    private Long transportationCosts;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2023-01-01",dataType = "string")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2021-01-11", type = "string")
    private LocalDate transportationDate;

    @Schema(example = "11톤", type = "string")
    @ApiModelProperty(example = "11톤",dataType = "string")
    private String transportationType;

    @Schema(example = "5000", type = "string")
    @ApiModelProperty(example = "5000",dataType = "string")
    private Long unitCost;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "2021-01-11", type = "string")
    @ApiModelProperty(example = "2021-01-11",dataType = "string")
    private Date createdAt;

    @Schema(example = "N", type = "string")
    @ApiModelProperty(example = "N",dataType = "string")
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
