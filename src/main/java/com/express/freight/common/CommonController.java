package com.express.freight.common;

import com.express.freight.common.dto.Category;
import com.express.freight.common.dto.PagingDto;
import com.express.freight.user.UserService;
import com.express.freight.util.JWTUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/common")
@Tag(name="Maintenance", description = "정비 API")
public class CommonController {

    private final CommonService commonService;
    private final UserService userService;

    public CommonController(CommonService commonService, UserService userService) {
        this.commonService = commonService;
        this.userService = userService;
    }

    @Tag(name="Search")
    @Operation(summary = "Search all Data", description = "통합 검색")
    @GetMapping("/{searchword}")
    public ResponseEntity<PagingDto<?>> getIntegratedSearch(
            HttpServletRequest request,
            @RequestParam(required = true) String searchword
    ) {
        try{
            /*Todo
             elastic search 사용?
             사용자 3명인데 통합검색있어야하냐..?
            */
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Tag(name="Excel")
    @Operation(summary = "Excel data", description = " 엑셀 데이터 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공 요청",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagingDto.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "category",
                    value = "operate, spend, maintenance, refuel 중 택 1",
                    required = true,
                    dataType = "string",
                    paramType = "path",
                    defaultValue = "operate"
            ),
            @ApiImplicitParam(
                    name = "date",
                    value = "날짜 (예: 'yyyy-MM' 또는 'yyyy')",
                    required = true,
                    dataType = "string",
                    paramType = "path",
                    defaultValue = "yyyy"
            )
    })
    @GetMapping("/{category}/{date}/excel")
    public ResponseEntity<PagingDto<?>> getExcelData(
        HttpServletRequest request,
        @PathVariable String category,
        @PathVariable String date
    ) {
        try {
            Category _category = Category.find(category.trim().toUpperCase());

            String userId = JWTUtil.getUserId(request.getHeader("Authorization"));

            if (!userService.isUser(userId))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            PagingDto<?> result = commonService.getExcelData(userId, _category.label(), date.trim());

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException t) {
            t.printStackTrace();
            return ResponseEntity.badRequest().body(new PagingDto<>(new ArrayList<>(), 0L, 0L));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
