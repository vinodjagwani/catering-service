package de.caterwings.catering.service.impl;

import de.caterwings.catering.entity.CateringProductEntity;
import de.caterwings.catering.repository.CateringProductReactiveRepository;
import de.caterwings.catering.service.CateringProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CateringProductServiceImpl implements CateringProductService {

    private final CateringProductReactiveRepository cateringProductReactiveRepository;


    @Override
    public Mono<CateringProductEntity> findById(final String id) {
        return cateringProductReactiveRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(final CateringProductEntity cateringProductEntity) {
        return cateringProductReactiveRepository.delete(cateringProductEntity);
    }

    @Override
    public Flux<CateringProductEntity> findAll(final Example<CateringProductEntity> example) {
        return cateringProductReactiveRepository.findAll(example);
    }

    @Override
    public Mono<CateringProductEntity> create(final CateringProductEntity cateringProductEntity) {
        return cateringProductReactiveRepository.save(cateringProductEntity);
    }
}
