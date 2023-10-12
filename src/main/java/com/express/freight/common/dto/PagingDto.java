package com.express.freight.common.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Example;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "페이징 데이터")
public class PagingDto<T> {

    @Schema(description = "데이터 목록")
    @ApiModelProperty("\"data\": [\n" +
            "    {\n" +
            "      \"createdAt\": \"2021-01-11\",\n" +
            "      \"delYn\": \"N\",\n" +
            "      \"id\": 0,\n" +
            "      \"loadingDate\": \"2021-01-11\",\n" +
            "      \"loadingPlace\": \"익산\",\n" +
            "      \"loadingRatio\": \"60\",\n" +
            "      \"transportationCosts\": \"39800\",\n" +
            "      \"transportationDate\": \"2021-01-11\",\n" +
            "      \"transportationType\": \"11톤\",\n" +
            "      \"unitCost\": \"5000\",\n" +
            "      \"unloadingDate\": \"2021-01-11\",\n" +
            "      \"unloadingPlace\": \"서울\",\n" +
            "      \"userId\": \"2001user001\"\n" +
            "    }")
    private List<T> data;

    @Schema(description = "전체 데이터 수")
    @ApiModelProperty(" \"totalCount\": 0,")
    private Long totalCount;

    @Schema(description = "총 합계")
    @ApiModelProperty(" \"totalMount\": 100,")
    private Long totalMount;

    public PagingDto(List<T> data, Long totalCount) {
        this.data = data;
        this.totalCount = totalCount;
    }

}
