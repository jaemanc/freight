package com.express.freight.operate.mapper;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.operate.dto.QOperateEntity;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OperateRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QOperateEntity qOperateEntity = QOperateEntity.operateEntity;


    public PagingDto<OperateDto> getOperateList(String userId, Pageable pageable, LocalDate date) {
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        List<OperateDto> operateDtoList = queryFactory
                .select(Projections.fields(OperateDto.class
                        ,qOperateEntity.id
                        ,qOperateEntity.createdAt
                        ,qOperateEntity.loadingDate
                        ,qOperateEntity.loadingRatio
                        ,qOperateEntity.loadingPlace
                        ,qOperateEntity.delYn
                        ,qOperateEntity.transportationCosts
                        ,qOperateEntity.transportationDate
                        ,qOperateEntity.transportationType
                        ,qOperateEntity.unitCost
                        ,qOperateEntity.unloadingDate
                        ,qOperateEntity.unloadingPlace
                        ,qOperateEntity.userId
                        ))
                .from(qOperateEntity)
                .where(
                        qOperateEntity.delYn.eq('N')
                        .and(qOperateEntity.loadingDate.between(firstDayOfMonth, lastDayOfMonth))
                        .and(qOperateEntity.userId.eq(userId))
                )
                .orderBy(qOperateEntity.loadingDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(qOperateEntity.count())
                .from(qOperateEntity)
                .where(
                        qOperateEntity.userId.eq(userId)
                                .and(qOperateEntity.loadingDate.between(firstDayOfMonth,lastDayOfMonth))
                                .and(qOperateEntity.delYn.eq('N'))
                )
                .fetchOne();

        Long totalMount = queryFactory
                .select(qOperateEntity.transportationCosts.sum())
                .from (qOperateEntity)
                .where(
                        qOperateEntity.userId.eq(userId)
                        .and(qOperateEntity.loadingDate.between(firstDayOfMonth,lastDayOfMonth))
                        .and(qOperateEntity.delYn.eq('N'))
                )
                .fetchOne();


        return new PagingDto<>(operateDtoList, totalCount, totalMount);
    }

}
