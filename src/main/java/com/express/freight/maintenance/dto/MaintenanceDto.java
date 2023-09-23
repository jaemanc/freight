package com.express.freight.maintenance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MaintenanceDto {

    private Long id;

    @ApiModelProperty(example = "2001user001", dataType = "string")
    private String userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2023-01-01", dataType = "string")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maintenanceDate;

    @ApiModelProperty(example = "5000", dataType = "string")
    private Long price;

    @ApiModelProperty(example = "만만카센타", dataType = "string")
    private String maintenanceShop;

    @ApiModelProperty(example = "정비 내용을 입력해주세요", dataType = "string")
    private String maintenanceHistory;

    @ApiModelProperty(example = "비고", dataType = "string")
    private String extra;

    @ApiModelProperty(example = "2023-01-01", dataType = "string")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "2021-01-11", type = "string")
    private Date createdAt;

    @ApiModelProperty(example = "N", dataType = "string")
    private Character delYn;

    @QueryProjection
    public MaintenanceDto(Long id, String userId, LocalDate maintenanceDate, Long price, String maintenanceShop, String maintenanceHistory, String extra, Date createdAt, Character delYn) {
        this.id = id;
        this.userId = userId;
        this.maintenanceDate = maintenanceDate;
        this.price = price;
        this.maintenanceShop = maintenanceShop;
        this.maintenanceHistory = maintenanceHistory;
        this.extra = extra;
        this.createdAt = createdAt;
        this.delYn = delYn;
    }
}
