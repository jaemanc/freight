package com.express.freight.refuel.dto;

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
@Table(name="TB_REFUELING")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class RefuelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @ApiModelProperty(example = "아이디")
    private Long id;

    @Column(name = "user_id")
    @ApiModelProperty(example = "사용자 아이디")
    private String userId;

    @Column(name = "refueling_date")
    @ApiModelProperty(example = "주유 날짜")
    private Date refuelingDate;

    @Column(name = "price")
    @ApiModelProperty(example = "금액")
    private Long price;

    @Column(name = "extra")
    @ApiModelProperty(example = "비고")
    private String extra;

    @CreatedDate
    @Column(name="created_at")
    private Date createdAt;

    @ApiModelProperty(example = "N")
    private Character delYn;

    public RefuelEntity(Long id, String userId, Date refuelingDate, Long price, String extra, Date createdAt, Character delYn) {
        this.id = id;
        this.userId = userId;
        this.refuelingDate = refuelingDate;
        this.price = price;
        this.extra = extra;
        this.createdAt = createdAt;
        this.delYn = delYn;
    }
}
