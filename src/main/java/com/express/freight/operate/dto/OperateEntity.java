package com.express.freight.operate.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name="TB_USER")
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
    private Long userId;

    @Column(name = "loading_date")
    @ApiModelProperty(example = "상차 날짜")
    private Date loadingDate;

    @Column(name = "loading_place")
    @ApiModelProperty(example = "상차지")
    private String loadingPlace;

    @Column(name = "unloading_date")
    @ApiModelProperty(example = "하차 날짜")
    private Date unloadingDate;

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
    private Date transportationDate;

    @Column(name = "transportation_type")
    @ApiModelProperty(example = "운송 품목")
    private String transportationType;

    @Column(name = "unit_cost")
    @ApiModelProperty(example = "운송 품목 별 단가")
    private Long unitCost;

    @CreatedDate
    @Column(name="created_at")
    private Date createdAt;

    public OperateEntity(Long id, Long userId, Date loadingDate, String loadingPlace, Date unloadingDate, String unloadingPlace, Long loadingRatio, Long transportationCosts, Date transportationDate, String transportationType, Long unitCost, Date createdAt) {
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
