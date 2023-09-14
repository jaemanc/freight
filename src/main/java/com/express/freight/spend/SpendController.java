package com.express.freight.spend;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/spend")
@Tag(name="Spend", description = "지출 API")
public class SpendController {

    private final SpendService spendService;

    public SpendController(SpendService spendService){
        this.spendService = spendService;
    }


}
