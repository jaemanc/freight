package com.express.freight.maintenance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/maintenance")
@Tag(name="MainTenance", description = "정비 API")
public class MaintenanceController {

    @Tag(name="MainTenance")
    @Operation(summary = "operation summary test", description = "operation description test !!")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK!!"),
            @ApiResponse(responseCode = "400", description = "BAD!!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND!!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
    })
    @GetMapping("/hello")
    public ResponseEntity<String> temp(){
        return ResponseEntity.ok(" hello world? ");
    }

}
