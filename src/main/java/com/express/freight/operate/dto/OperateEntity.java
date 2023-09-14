package com.express.freight.operate.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name="TB_OPERATE")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class OperateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @ApiModelProperty(example = "아이디")
    private Long id;

    @Column(name = "user_id")
    @ApiModelProperty(example = "사용자 아이디")
    private String userId;

    @Column(name = "loading_date")
    @ApiModelProperty(example = "상차 날짜")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate loadingDate;

    @Column(name = "loading_place")
    @ApiModelProperty(example = "상차지")
    private String loadingPlace;

    @Column(name = "unloading_date")
    @ApiModelProperty(example = "하차 날짜")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate unloadingDate;

    @Column(name = "unloading_place")
    @ApiModelProperty(example = "하차지")
    private String unloadingPlace;

    @Column(name = "loading_ratio")
    @ApiModelProperty(example = "요율")
    private Long loadingRatio;

    @Column(name = "transportation_costs")
    @ApiModelProperty(example = "운반비")
    private Long transportationCosts;

    @Column(name = "transportation_date")
    @ApiModelProperty(example = "운반 날짜")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transportationDate;

    @Column(name = "transportation_type")
    @ApiModelProperty(example = "운송 품목")
    private String transportationType;

    @Column(name = "unit_cost")
    @ApiModelProperty(example = "운송 품목 별 단가")
    private Long unitCost;

    @CreatedDate
    @Column(name="created_at")
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @ApiModelProperty(example = "N")
    @Setter
    private Character delYn;

    public OperateEntity(Long id, String userId, LocalDate loadingDate, String loadingPlace, LocalDate unloadingDate, String unloadingPlace, Long loadingRatio, Long transportationCosts, LocalDate transportationDate, String transportationType, Long unitCost, Date createdAt, Character delYn) {
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

    public void updateOperate(OperateDto operateDto) {
        if (operateDto.getLoadingDate() != null) {
            this.loadingDate = operateDto.getLoadingDate();
        }
        if (operateDto.getLoadingPlace() != null) {
            this.loadingPlace = operateDto.getLoadingPlace();
        }
        if (operateDto.getUnloadingDate() != null) {
            this.unloadingDate = operateDto.getUnloadingDate();
        }
        if (operateDto.getUnloadingPlace() != null) {
            this.unloadingPlace = operateDto.getUnloadingPlace();
        }
        if (operateDto.getLoadingRatio() != null) {
            this.loadingRatio = operateDto.getLoadingRatio();
        }
        if (operateDto.getTransportationCosts() != null) {
            this.transportationCosts = operateDto.getTransportationCosts();
        }
        if (operateDto.getTransportationDate() != null) {
            this.transportationDate = operateDto.getTransportationDate();
        }
        if (operateDto.getTransportationType() != null) {
            this.transportationType = operateDto.getTransportationType();
        }
        if (operateDto.getUnitCost() != null) {
            this.unitCost = operateDto.getUnitCost();
        }
    }

}
