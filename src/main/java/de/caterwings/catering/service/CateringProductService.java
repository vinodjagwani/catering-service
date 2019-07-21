package de.caterwings.catering.service;

import de.caterwings.catering.entity.CateringProductEntity;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CateringProductService {

    Mono<CateringProductEntity> findById(final String id);

    Mono<Void> delete(final CateringProductEntity cateringProductEntity);

    Flux<CateringProductEntity> findAll(final Example<CateringProductEntity> example);

    Mono<CateringProductEntity> create(final CateringProductEntity cateringProductEntity);

}
