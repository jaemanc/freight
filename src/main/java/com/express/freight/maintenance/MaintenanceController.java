package com.express.freight.maintenance;

import com.express.freight.maintenance.dto.MaintenanceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/maintenance")
@Tag(name="MainTenance", description = "정비 API")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    public MaintenanceController(MaintenanceService maintenanceService){
        this.maintenanceService = maintenanceService;
    }

    @Tag(name="MainTenance")
    @Operation(summary = "operation summary test", description = "operation description test !!")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK!!"),
            @ApiResponse(responseCode = "400", description = "BAD!!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND!!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
    })
    @GetMapping("/hello")
    public ResponseEntity<String> tempapi() {
        return ResponseEntity.ok(" hello world? ");
    }

    @Tag(name="MainTenance")
    @Operation(summary = "insert maintenance info tb_maintenance", description = "정비 내역 등록")
    @PostMapping("")
    public ResponseEntity<MaintenanceDto> postMaintenance(
            @RequestBody MaintenanceDto maintenanceDto
    ){
        try{
            maintenanceDto = maintenanceService.postMaintenance(maintenanceDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(maintenanceDto);
    }



}
