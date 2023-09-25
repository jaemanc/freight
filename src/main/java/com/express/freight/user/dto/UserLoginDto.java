package com.express.freight.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginDto {

    @ApiModelProperty(example = "handsomeManMan01", dataType = "string")
    private String userId;

    @ApiModelProperty(example = "죠르디1", dataType = "string")
    private String name;

    @ApiModelProperty(example = "dtu887o76askrnglskadrin2-sr7580ykgnld2-uopo078slrngmls11", dataType = "string")
    private String jwt;

}
