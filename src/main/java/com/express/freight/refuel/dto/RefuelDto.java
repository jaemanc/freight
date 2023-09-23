package com.express.freight.refuel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RefuelDto {
    private Long id;

    @ApiModelProperty(example = "2001user001", dataType = "string")
    private String userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2023-01-01", dataType = "string")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate refuelingDate;

    @ApiModelProperty(example = "5000", dataType = "string")
    private Long price;

    @ApiModelProperty(example = "기타 등등 사항 적으세요", dataType = "string")
    private String extra;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "2021-01-11", type = "string")
    @ApiModelProperty(example = "2021-01-11",dataType = "string")
    private Date createdAt;

    @ApiModelProperty(example = "N",dataType = "string")
    private Character delYn;

    @QueryProjection
    public RefuelDto(Long id, String userId, LocalDate refuelingDate, Long price, String extra, Date createdAt, Character delYn) {
        this.id = id;
        this.userId = userId;
        this.refuelingDate = refuelingDate;
        this.price = price;
        this.extra = extra;
        this.createdAt = createdAt;
        this.delYn = delYn;
    }
}
