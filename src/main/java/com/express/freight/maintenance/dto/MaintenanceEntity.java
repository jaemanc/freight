package com.express.freight.maintenance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
@Getter
@NoArgsConstructor
@Table(name="TB_MAINTENANCE")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @ApiModelProperty(example = "아이디")
    private Long id;

    @Column(name="user_id")
    @ApiModelProperty(example = "사용자 아이디")
    private String userId;

    @Column(name="maintenance_date")
    @ApiModelProperty(example = "정비 날짜")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maintenanceDate;

    @Column(name="price")
    @ApiModelProperty(example = "금액")
    private Long price;

    @Column(name="maintenance_shop")
    @ApiModelProperty(example = "정비소")
    private String maintenanceShop;

    @Column(name="maintenance_history")
    @ApiModelProperty(example = "정비 내용")
    private String maintenanceHistory;

    @Column(name="extra")
    @ApiModelProperty(example = "비고")
    private String extra;

    @CreatedDate
    @Column(name="created_at")
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @ApiModelProperty(example = "N")
    private Character delYn;

    public MaintenanceEntity(Long id, String userId, LocalDate maintenanceDate, Long price, String maintenanceShop, String maintenanceHistory, String extra, Date createdAt, Character delYn) {
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
