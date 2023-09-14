package com.express.freight.spend;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.operate.dto.OperateEntity;
import com.express.freight.operate.mapper.OperateMapper;
import com.express.freight.spend.dto.SpendDto;
import com.express.freight.spend.dto.SpendEntity;
import com.express.freight.spend.mapper.SpendMapper;
import com.express.freight.spend.mapper.SpendRepository;
import com.express.freight.spend.mapper.SpendRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SpendService {

    private final SpendRepository spendRepository;
    private final SpendRepositoryCustom spendRepositoryCustom;


    public SpendService(SpendRepository spendRepository, SpendRepositoryCustom spendRepositoryCustom) {
        this.spendRepository = spendRepository;
        this.spendRepositoryCustom = spendRepositoryCustom;
    }

    public SpendDto postSpend(SpendDto spendDto) {

        SpendEntity entity = SpendMapper.mapper.toEntity(spendDto);
        entity = spendRepository.save(entity);

        return SpendMapper.mapper.toDto(entity);

    }

    public PagingDto<SpendDto> getSpendList(String userId, Pageable pageable, LocalDate date) {
        return spendRepositoryCustom.getSpendList(userId, pageable, date);
    }

    public SpendDto getSpendDetail(Long id){
        SpendEntity spendEntity = spendRepository.getSpendEntityById(id);
        return SpendMapper.mapper.toDto(spendEntity);
    }

    public SpendDto putSpend(SpendDto spendDto) {
        Optional<SpendEntity> entity = spendRepository.findById(spendDto.getId());

        if (entity.isPresent()) {
            SpendEntity target = entity.get();
            target.updateSpend(spendDto);
            spendRepository.save(target);

            return SpendMapper.mapper.toDto(target);
        }
        return null;
    }

    public void deleteSpend(Long id) {
        Optional<SpendEntity> entity = spendRepository.findById(id);

        if (entity.isPresent()) {
            SpendEntity target = entity.get();
            target.setDelYn('Y');
            spendRepository.save(target);
        }
    }



}
