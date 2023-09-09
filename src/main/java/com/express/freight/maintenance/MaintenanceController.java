package com.express.freight.maintenance;

import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.user.UserService;
import com.express.freight.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/maintenance")
@Tag(name="MainTenance", description = "정비 API")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final UserService userService;
    public MaintenanceController(MaintenanceService maintenanceService, UserService userService){
        this.maintenanceService = maintenanceService;
        this.userService = userService;
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

    // 조회 + 총 운반비
    // param ( 페이징 , 카운트,
    @Tag(name="MainTenance")
    @Operation(summary = "select tb_maintenance list", description = "정비 내역 목록 조회")
    @GetMapping("")
    public ResponseEntity<List<MaintenanceDto>> getMaintenance(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            HttpServletRequest request
    ) {

        String authorizationHeader = request.getHeader("Authorization");

        List<MaintenanceDto> maintenanceList = null;
        try{

            String userId = JWTUtil.getUserId(authorizationHeader);
            Pageable pageable = PageRequest.of(page, size);

            maintenanceList = maintenanceService.getMaintenanaceList(userId, pageable, date);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return ResponseEntity.ok(maintenanceList);
    }





    // 상세

    // 수정

    // 삭제




}
