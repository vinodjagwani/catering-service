package de.caterwings.catering.facade;

import de.caterwings.catering.dto.CateringProductRequest;
import de.caterwings.catering.dto.CateringProductResponse;
import de.caterwings.catering.entity.CateringProductEntity;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CateringProductFacade {

    Mono<Void> deleteCateringProduct(final String id);

    Flux<CateringProductResponse> findAllCateringProducts(final Example<CateringProductEntity> example);

    Mono<CateringProductResponse> createCateringProduct(final CateringProductRequest cateringProductRequest);

    Mono<CateringProductResponse> updateCateringProduct(final String id, final CateringProductRequest cateringProductRequest);
}
