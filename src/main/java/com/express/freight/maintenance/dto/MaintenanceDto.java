package com.express.freight.maintenance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "아이디")
    private Long id;

    @ApiModelProperty(example = "사용자 아이디")
    private String userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2023-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maintenanceDate;

    @ApiModelProperty(example = "금액")
    private Long price;

    @ApiModelProperty(example = "정비소")
    private String maintenanceShop;

    @ApiModelProperty(example = "정비 내용")
    private String maintenanceHistory;

    @ApiModelProperty(example = "비고")
    private String extra;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @ApiModelProperty(example = "N")
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
