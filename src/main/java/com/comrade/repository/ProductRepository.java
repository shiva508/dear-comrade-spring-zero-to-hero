package com.comrade.repository;

import com.comrade.domine.ProductEntity;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {

    public Flux<ProductEntity> findByPriceBetween(Range<Integer> range);
}
