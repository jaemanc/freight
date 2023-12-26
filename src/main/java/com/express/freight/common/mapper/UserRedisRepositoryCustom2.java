package com.express.freight.common.mapper;

import com.express.freight.common.dto.RedisEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRedisRepositoryCustom2 implements UserRedisRepository2{
    @Override
    public <S extends RedisEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends RedisEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<RedisEntity> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<RedisEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<RedisEntity> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    public void deleteById(String s) {

    }

    @Override
    public void delete(RedisEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends RedisEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
