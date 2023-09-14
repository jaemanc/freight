package com.express.freight.spend.dto;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

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
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @ApiModelProperty(example = "N")
    @Setter
    private Character delYn;
    public SpendEntity(Long id, String userId, LocalDate paymentDate, Long price, String paymentDetail, String extra, Date createdAt, Character delyn) {
        this.id = id;
        this.userId = userId;
        this.paymentDate = paymentDate;
        this.price = price;
        this.paymentDetail = paymentDetail;
        this.extra = extra;
        this.createdAt = createdAt;
        this.delYn = delyn;
    }

    public void updateSpend(SpendDto spendDto) {
        if (spendDto.getPaymentDate() != null) {
            this.paymentDate = spendDto.getPaymentDate();
        }
        if (spendDto.getPrice() != null) {
            this.price = spendDto.getPrice();
        }
        if (spendDto.getPaymentDetail() != null) {
            this.paymentDetail = spendDto.getPaymentDetail();
        }
        if (spendDto.getExtra() != null) {
            this.extra = spendDto.getExtra();
        }
        if (spendDto.getDelYn() != null) {
            this.delYn = spendDto.getDelYn();
        }
    }

}
