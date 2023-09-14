package com.express.freight.spend.mapper;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.operate.dto.QOperateEntity;
import com.express.freight.spend.dto.QSpendEntity;
import com.express.freight.spend.dto.SpendDto;
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
public class SpendRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QSpendEntity qSpendEntity = QSpendEntity.spendEntity;

    public PagingDto<SpendDto> getSpendList(String userId, Pageable pageable, LocalDate date) {
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        List<SpendDto> spendDtoList = queryFactory
                .select(Projections.fields(SpendDto.class
                        ,qSpendEntity.id
                        ,qSpendEntity.createdAt
                        ,qSpendEntity.userId
                        ,qSpendEntity.extra
                        ,qSpendEntity.paymentDate
                        ,qSpendEntity.paymentDetail
                        ,qSpendEntity.price
                ))
                .from(qSpendEntity)
                .where(
                        qSpendEntity.delYn.eq('N')
                                .and(qSpendEntity.paymentDate.between(firstDayOfMonth,lastDayOfMonth))
                                .and(qSpendEntity.userId.eq(userId))
                )
                .orderBy(qSpendEntity.paymentDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(qSpendEntity.count())
                .from(qSpendEntity)
                .where(
                        qSpendEntity.userId.eq(userId)
                                .and(qSpendEntity.paymentDate.between(firstDayOfMonth,lastDayOfMonth))
                                .and(qSpendEntity.delYn.eq('N'))
                )
                .fetchOne();

        return new PagingDto<>(spendDtoList, totalCount);

    }


}
