package com.express.freight.spend.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
public class SpendDto {
    private Long id;

    @ApiModelProperty(example = "2001user001", dataType = "string")
    private String userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2023-01-01", dataType = "string")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @ApiModelProperty(example = "5000", dataType = "string")
    private Long price;

    @ApiModelProperty(example = "차량용품 1000원 지출 했음.", dataType = "string")
    private String paymentDetail;

    @ApiModelProperty(example = "비고 비고 비에고", dataType = "string")
    private String extra;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "2021-01-11", type = "string")
    @ApiModelProperty(example = "2021-01-11",dataType = "string")
    private Date createdAt;

    @ApiModelProperty(example = "N", dataType = "string")
    private Character delYn;

    @QueryProjection
    public SpendDto(Long id, String userId, LocalDate paymentDate, Long price, String paymentDetail, String extra, Date createdAt, Character delYn) {
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
