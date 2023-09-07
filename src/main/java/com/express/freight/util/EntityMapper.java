package com.express.freight.util;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;

public interface EntityMapper<D, E> {

    E toEntity(final D dto);
    D toDto(final E entity);

    List<E> toEntityList(final List<D> dto);

    Set<E> toEntitySet(final Set<D> dto);

    List<D> toDtoList(final List<E> Entity);

    Set<D> toDtoSet(final Set<E> Entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(D dto, @MappingTarget E entity);
}
