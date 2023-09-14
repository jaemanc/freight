package com.express.freight.refuel;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.refuel.dto.RefuelDto;
import com.express.freight.refuel.dto.RefuelEntity;
import com.express.freight.refuel.mapper.RefuelMapper;
import com.express.freight.refuel.mapper.RefuelRepository;
import com.express.freight.refuel.mapper.RefuelRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RefuelService {

    private final RefuelRepository refuelRepository;
    private final RefuelRepositoryCustom refuelRepositoryCustom;

    public RefuelService(RefuelRepository refuelRepository, RefuelRepositoryCustom refuelRepositoryCustom) {
        this.refuelRepository = refuelRepository;
        this.refuelRepositoryCustom = refuelRepositoryCustom;
    }

    public RefuelDto postRefuel(RefuelDto refuelDto) {
        RefuelEntity refuelEntity = RefuelMapper.mapper.toEntity(refuelDto);
        refuelEntity = refuelRepository.save(refuelEntity);
        return RefuelMapper.mapper.toDto(refuelEntity);
    }

    public PagingDto<RefuelDto> getRefuelList(String userId, Pageable pageable, LocalDate date) {

        return refuelRepositoryCustom.getRefuelList(userId, pageable, date);

    }

}
