package com.express.freight.refuel;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.refuel.dto.RefuelDto;
import com.express.freight.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            @RequestBody RefuelDto refuelDto,
            HttpServletRequest request
    ){
        try{
            //Todo


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(refuelDto);
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
            //Todo


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(null);
    }

    @Tag(name="Refuel")
    @Operation(summary = "get refuel info detail from tb_refueling", description = "주유 내역 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<RefuelDto> getRefuelingDetail(
            @PathVariable Long id
    ){
        try{
            //Todo


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(null);
    }

    @Tag(name="Refuel")
    @Operation(summary = "delete refuel info detail from tb_refueling", description = "주유 내역 상세 조회")
    @DeleteMapping("/{id}")
    public ResponseEntity<RefuelDto> deleteRefueling(
            @PathVariable Long id
    ){
        try{
            //Todo


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(null);
    }

}
