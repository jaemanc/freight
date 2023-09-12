package com.express.freight.maintenance;

import com.express.freight.common.dto.PagingDto;
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
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/maintenance")
@Tag(name="Maintenance", description = "정비 API")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final UserService userService;
    public MaintenanceController(MaintenanceService maintenanceService, UserService userService){
        this.maintenanceService = maintenanceService;
        this.userService = userService;
    }

    @Tag(name="Maintenance")
    @Operation(summary = "insert maintenance info tb_maintenance", description = "정비 내역 등록")
    @PostMapping("")
    public ResponseEntity<MaintenanceDto> postMaintenance(
            @RequestBody MaintenanceDto maintenanceDto,
            HttpServletRequest request
    ){
        try{
            maintenanceDto.setUserId(JWTUtil.getUserId(request.getHeader("Authorization")));

            maintenanceDto = maintenanceService.postMaintenance(maintenanceDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(maintenanceDto);
    }

    // 조회 + 총 운반비
    // param ( 페이징 , 카운트,
    @Tag(name="Maintenance")
    @Operation(summary = "select tb_maintenance list", description = "정비 내역 목록 조회")
    @GetMapping("")
    public ResponseEntity<PagingDto<MaintenanceDto>> getMaintenanceList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            HttpServletRequest request
    ) {
        try{

            String userId = JWTUtil.getUserId(request.getHeader("Authorization"));
            Pageable pageable = PageRequest.of(page-1, size);

            PagingDto<MaintenanceDto> maintenanceList = maintenanceService.getMaintenanceList(userId, pageable, date);

            return ResponseEntity.ok(maintenanceList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


    // 상세
    @Tag(name="Maintenance")
    @Operation(summary = "select tb_maintenance detail", description = "정비 내역 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDto> getMaintenanceDetail(
            @RequestParam(required = true) Long id,
            HttpServletRequest request
    ) {
        try{

            String userId = JWTUtil.getUserId(request.getHeader("Authorization"));

            MaintenanceDto maintenanceDto = maintenanceService.getMaintenanceDetail(userId,id);
            if (ObjectUtils.isEmpty(maintenanceDto))
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(maintenanceDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

    }


    // 수정
    @Tag(name="Maintenance")
    @Operation(summary = "update tb_maintenance detail", description = "정비 내역 수정")
    @PutMapping("")
    public ResponseEntity<MaintenanceDto> putMaintenance(
            @RequestBody(required = true) MaintenanceDto maintenanceDto
    ) {
        try{
            MaintenanceDto result = maintenanceService.putMaintenanace(maintenanceDto);
            if (ObjectUtils.isEmpty(result)) {
                return  ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


    // 삭제
    @Tag(name="Maintenance")
    @Operation(summary = "delete tb_maintenance detail", description = "정비 내역 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<MaintenanceDto> deleteMaintenance(
            @RequestParam(required = true) Long id
    ) {

        try{
            maintenanceService.deleteMaintenance(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }



}
