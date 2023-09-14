package com.express.freight.refuel.mapper;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.operate.dto.QOperateEntity;
import com.express.freight.refuel.RefuelController;
import com.express.freight.refuel.dto.QRefuelEntity;
import com.express.freight.refuel.dto.RefuelDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RefuelRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QRefuelEntity qRefuelEntity = QRefuelEntity.refuelEntity;

    public PagingDto<RefuelDto> getRefuelList(String userId, Pageable pageable, LocalDate date) {
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        List<RefuelDto> refuelDtoList = queryFactory
                .select(Projections.fields(RefuelDto.class
                        ,qRefuelEntity.id
                        ,qRefuelEntity.userId
                        ,qRefuelEntity.refuelingDate
                        ,qRefuelEntity.delYn
                        ,qRefuelEntity.price
                        ,qRefuelEntity.extra
                        ))
                .from(qRefuelEntity)
                .where(
                        qRefuelEntity.delYn.eq('N')
                        .and(qRefuelEntity.refuelingDate.between(firstDayOfMonth, lastDayOfMonth))
                        .and(qRefuelEntity.userId.eq(userId))
                )
                .orderBy(qRefuelEntity.refuelingDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory.select(qRefuelEntity.count())
                .from(qRefuelEntity)
                .where(
                        qRefuelEntity.userId.eq(userId)
                                .and(qRefuelEntity.refuelingDate.between(firstDayOfMonth, lastDayOfMonth))
                                .and(qRefuelEntity.delYn.eq('N'))
                )
                .fetchOne();

        return new PagingDto<>(refuelDtoList, totalCount);
    }


}
