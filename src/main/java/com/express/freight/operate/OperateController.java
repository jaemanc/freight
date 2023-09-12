package com.express.freight.operate;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.user.UserService;
import com.express.freight.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
@RequestMapping(value="/api/v1/operate")
@Tag(name="Operate", description = "운행 API")
public class OperateController {

    private final OperateService operateService;
    private final UserService userService;

    public OperateController(OperateService operateService, UserService userService){
        this.operateService = operateService;
        this.userService = userService;
    }

    @Tag(name="Operate")
    @Operation(summary = "insert operate info tb_operate", description = "운행 내역 등록")
    @PostMapping("")
    public ResponseEntity<OperateDto> postOperate(
            @RequestBody OperateDto operateDto,
            HttpServletRequest request
    ){
        try{
            operateDto.setUserId(JWTUtil.getUserId(request.getHeader("Authorization")));

            operateDto = operateService.postOperate(operateDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(operateDto);
    }

    @Tag(name="Operate")
    @Operation(summary = "insert operate historys info from tb_operate", description = "운행 내역 목록 조회")
    @GetMapping("")
    public ResponseEntity<PagingDto<OperateDto>> getOperateList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            HttpServletRequest request
    ){
        try{
            String userId = JWTUtil.getUserId(request.getHeader("Authorization"));
            Pageable pageable = PageRequest.of(page-1, size);

            PagingDto<OperateDto> operateDtoList = operateService.getOperateList(userId, pageable, date);

            return ResponseEntity.ok(operateDtoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @Tag(name="Operate")
    @Operation(summary = "get operate info from tb_operate", description = "운행 내역 상세 조회")
    @PostMapping("")
    public ResponseEntity<OperateDto> getOperateDetail(
            @RequestParam(required = true) Long id,
            HttpServletRequest request
    ){
        try{
            String userId = JWTUtil.getUserId(request.getHeader("Authorization"));
            OperateDto operateDto = operateService.getOperateDetail(id);
            return ResponseEntity.ok(operateDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Tag(name="Operate")
    @Operation(summary = "patch operate info tb_operate", description = "운행 내역 수정")
    @PutMapping("/{id}")
    public ResponseEntity<OperateDto> putOperate(
            @RequestBody OperateDto operateDto
    ){
        try{
            operateDto = operateService.putOperate(operateDto);
            return ResponseEntity.ok(operateDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Tag(name="Operate")
    @Operation(summary = "delete operate info tb_operate", description = "운행 내역 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOperateDetail(
            @RequestParam(required = true) Long id
    ){
        try{
            operateService.deleteOperate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }


}
