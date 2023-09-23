package com.express.freight.spend;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.spend.dto.SpendDto;
import com.express.freight.util.DataChkUtil;
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
@RequestMapping(value="/api/v1/spend")
@Tag(name="Spend", description = "지출 API")
public class SpendController {

    private final SpendService spendService;

    public SpendController(SpendService spendService){
        this.spendService = spendService;
    }

    @Tag(name="Spend")
    @Operation(summary = "insert spend info tb_spend ", description = "지출 내역 등록")
    @PostMapping("")
    public ResponseEntity<SpendDto> postSpend(
            @RequestBody SpendDto spendDto,
            HttpServletRequest request
    ){
        try{
            spendDto.setUserId(JWTUtil.getUserId(request.getHeader("Authorization")));

            spendDto = spendService.postSpend(spendDto);
            return ResponseEntity.ok(spendDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @Tag(name="Spend")
    @Operation(summary = "get spend history info from tb_spend ", description = "지출 내역 목록 조회")
    @GetMapping("")
    public ResponseEntity<PagingDto<SpendDto>> getSpendList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            HttpServletRequest request
    ){
        try{

            String userId = JWTUtil.getUserId(request.getHeader("Authorization"));
            Pageable pageable = PageRequest.of(page-1, size);
            PagingDto<SpendDto> spendDtoList = spendService.getSpendList(userId,pageable,date);
            if (DataChkUtil.isEmpty(spendDtoList)) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(spendDtoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Tag(name="Spend")
    @Operation(summary = "get spend info from tb_spend ", description = "지출 내역 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<SpendDto> getSpendDetail(
            @PathVariable Long id
    ){
        try{
            SpendDto spendDto = spendService.getSpendDetail(id);
            return ResponseEntity.ok(spendDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @Tag(name="Spend")
    @Operation(summary = "patch spend info from tb_spend ", description = "지출 내역 수정")
    @PutMapping("")
    public ResponseEntity<SpendDto> putSpend(
            @RequestBody SpendDto spendDto
    ) {
        try {
            spendDto = spendService.putSpend(spendDto);
            return ResponseEntity.ok(spendDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Tag(name="Spend")
    @Operation(summary = "delete spend info from tb_spend ", description = "지출 내역 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<SpendDto> deleteSpend(
            @PathVariable Long id
    ){
        try{
            spendService.deleteSpend(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


}
