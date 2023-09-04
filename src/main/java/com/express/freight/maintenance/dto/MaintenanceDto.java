package com.express.freight.maintenance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MaintenanceDto {

    private Long id;

    private Long userId;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date maintenanceDate;

    private Long price;

    private String maintenanceShop;

    private String maintenanceHistory;

    private String extra;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @QueryProjection
    public MaintenanceDto(Long id, Long userId, Date maintenanceDate, Long price, String maintenanceShop, String maintenanceHistory, String extra, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.maintenanceDate = maintenanceDate;
        this.price = price;
        this.maintenanceShop = maintenanceShop;
        this.maintenanceHistory = maintenanceHistory;
        this.extra = extra;
        this.createdAt = createdAt;
    }
}
