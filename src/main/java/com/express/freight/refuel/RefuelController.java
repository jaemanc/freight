package com.express.freight.refuel;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.refuel.dto.RefuelDto;
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
@RequestMapping(value="/api/v1/refuel")
@Tag(name="Refuel", description = "주유 API")
public class RefuelController {

    private final RefuelService refuelService;

    public RefuelController(RefuelService refuelService) {
        this.refuelService = refuelService;
    }

    @Tag(name="Refuel")
    @Operation(summary = "insert refuel info tb_refueling", description = "주유 내역 등록")
    @PostMapping("")
    public ResponseEntity<RefuelDto> postRefueling(
            @RequestBody RefuelDto refuelDto
    ){
        try{
            refuelDto = refuelService.postRefuel(refuelDto);
            return ResponseEntity.ok(refuelDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Tag(name="Refuel")
    @Operation(summary = "get refuel history info tb_refueling", description = "주유 내역 목록 조회")
    @GetMapping("")
    public ResponseEntity<PagingDto<RefuelDto>> getRefuelingList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            HttpServletRequest request
    ){
        try{
            String userId = JWTUtil.getUserId(request.getHeader("Authorization"));
            Pageable pageable = PageRequest.of(page-1, size);
            PagingDto<RefuelDto> pagingDto = refuelService.getRefuelList(userId, pageable, date);
            return ResponseEntity.ok(pagingDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @Tag(name="Refuel")
    @Operation(summary = "get refuel info detail from tb_refueling", description = "주유 내역 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<RefuelDto> getRefuelingDetail(
            @PathVariable Long id
    ){
        try{
            RefuelDto refuelDto = refuelService.getRefuelingDetail(id);
            return ResponseEntity.ok(refuelDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @Tag(name="Refuel")
    @Operation(summary = "patch refuel tb_refueling", description = "주유 내역 수정")
    @PutMapping("")
    public ResponseEntity<RefuelDto> putRefueling(
            @RequestBody RefuelDto refuelDto
    ){
        try{
            refuelDto = refuelService.putRefueling(refuelDto);
            return ResponseEntity.ok(refuelDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Tag(name="Refuel")
    @Operation(summary = "delete refuel tb_refueling", description = "주유 내역 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<RefuelDto> deleteRefueling(
            @PathVariable Long id
    ){
        try{
            refuelService.deleteRefueling(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
