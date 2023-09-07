package com.express.freight.spend.dto;

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
@Table(name="TB_SPEND")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class SpendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @ApiModelProperty(example = "아이디")
    private Long id;

    @Column(name="user_id")
    @ApiModelProperty(example = "사용자 아이디")
    private String userId;

    @Column(name="payment_date")
    @ApiModelProperty(example = "지출 날짜")
    private Date paymentDate;

    @Column(name="price")
    @ApiModelProperty(example = "금액")
    private Long price;

    @Column(name="payment_detail")
    @ApiModelProperty(example = "지출 내용")
    private String paymentDetail;

    @Column(name="extra")
    @ApiModelProperty(example = "비고")
    private String extra;

    @CreatedDate
    @Column(name="created_at")
    private Date createdAt;

    @ApiModelProperty(example = "N")
    private Character delYn;
    public SpendEntity(Long id, String userId, Date paymentDate, Long price, String paymentDetail, String extra, Date createdAt, Character delyn) {
        this.id = id;
        this.userId = userId;
        this.paymentDate = paymentDate;
        this.price = price;
        this.paymentDetail = paymentDetail;
        this.extra = extra;
        this.createdAt = createdAt;
        this.delYn = delyn;
    }
}
