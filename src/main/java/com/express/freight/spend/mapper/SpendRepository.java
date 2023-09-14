package com.express.freight.spend.mapper;

import com.express.freight.spend.dto.SpendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendRepository extends JpaRepository<SpendEntity, Object> {

    SpendEntity getSpendEntityById(Long id);
}
