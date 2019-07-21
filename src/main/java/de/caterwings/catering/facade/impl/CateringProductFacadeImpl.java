package de.caterwings.catering.facade.impl;

import de.caterwings.catering.constant.CateringConstant;
import de.caterwings.catering.constant.ErrorCodeEnum;
import de.caterwings.catering.dto.CateringProductRequest;
import de.caterwings.catering.dto.CateringProductResponse;
import de.caterwings.catering.entity.CateringProductEntity;
import de.caterwings.catering.exception.CateringServiceException;
import de.caterwings.catering.facade.CateringProductFacade;
import de.caterwings.catering.service.CateringProductService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CateringProductFacadeImpl implements CateringProductFacade {


    @Autowired
    private Mapper dozerMapper;

    @Autowired
    private CateringProductService cateringProductService;


    @Override
    public Mono<Void> deleteCateringProduct(final String id) {
        return cateringProductService.findById(id)
                .switchIfEmpty(Mono.error(new CateringServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "catering product not found [" + id + "]")))
                .flatMap(cateringProductEntity -> cateringProductService.delete(cateringProductEntity));
    }

    @Override
    public Flux<CateringProductResponse> findAllCateringProducts(final Example<CateringProductEntity> example) {
        final Flux<CateringProductEntity> productEntityFlux = cateringProductService.findAll(example);
        return productEntityFlux.map(product -> dozerMapper.map(product, CateringProductResponse.class, CateringConstant.CAT_PROD_E_RS_ID));
    }

    @Override
    public Mono<CateringProductResponse> createCateringProduct(final CateringProductRequest cateringProductRequest) {
        final CateringProductEntity entity = dozerMapper.map(cateringProductRequest, CateringProductEntity.class, CateringConstant.CAT_PROD_R_E_ID);
        return cateringProductService.create(entity).flatMap(p -> Mono.just(dozerMapper.map(p, CateringProductResponse.class, CateringConstant.CAT_PROD_E_RS_ID)));
    }

    @Override
    public Mono<CateringProductResponse> updateCateringProduct(final String id, final CateringProductRequest cateringProductRequest) {
        return cateringProductService.findById(id)
                .switchIfEmpty(Mono.error(new CateringServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "catering product not found [" + id + "]")))
                .flatMap(cateringProduct -> {
                    dozerMapper.map(cateringProductRequest, cateringProduct, CateringConstant.CAT_PROD_R_E_ID);
                    return cateringProductService.create(cateringProduct);
                }).map(updateCateringProduct -> dozerMapper.map(updateCateringProduct, CateringProductResponse.class, CateringConstant.CAT_PROD_E_RS_ID));

    }

}
