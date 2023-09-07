package com.express.freight.spend.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@NoArgsConstructor
public class SpendDto {
    private Long id;

    private String userId;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date paymentDate;

    private Long price;

    private String paymentDetail;

    private String extra;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @ApiModelProperty(example = "N")
    private Character delYn;
    @QueryProjection
    public SpendDto(Long id, String userId, Date paymentDate, Long price, String paymentDetail, String extra, Date createdAt, Character delYn) {
        this.id = id;
        this.userId = userId;
        this.paymentDate = paymentDate;
        this.price = price;
        this.paymentDetail = paymentDetail;
        this.extra = extra;
        this.createdAt = createdAt;
        this.delYn = delYn;
    }
}
