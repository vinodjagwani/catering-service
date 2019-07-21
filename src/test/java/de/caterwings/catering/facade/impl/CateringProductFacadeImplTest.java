package de.caterwings.catering.facade.impl;


import de.caterwings.catering.AbstractCateringTest;
import de.caterwings.catering.dto.CateringProductRequest;
import de.caterwings.catering.entity.CateringProductEntity;
import de.caterwings.catering.service.CateringProductService;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CateringProductFacadeImplTest extends AbstractCateringTest {


    @Mock
    private Mapper dozerMapper;

    @Mock
    private CateringProductService cateringProductService;

    @InjectMocks
    private CateringProductFacadeImpl cateringProductFacadeImp;


    @Test
    public void deleteCateringProductTest() {
        when(cateringProductService.findById(any(String.class))).thenReturn(Mono.just(getCateringProductEntity()));
        cateringProductFacadeImp.deleteCateringProduct("1231");
        verify(cateringProductService, times(1)).findById(any(String.class));
    }

    @Test
    public void findAllCateringProductsTest() {
        when(cateringProductService.findAll(any(Example.class))).thenReturn(Flux.just(getCateringProductEntity()));
        cateringProductFacadeImp.findAllCateringProducts(Example.of(getCateringProductEntity()));
        verify(cateringProductService, times(1)).findAll(any(Example.class));
    }

    @Test
    public void createCateringProductTest() {
        final CateringProductEntity entity = getCateringProductEntity();
        when(dozerMapper.map(any(), any(), any())).thenReturn(entity);
        when(cateringProductService.create(any(CateringProductEntity.class))).thenReturn(Mono.just(entity));
        cateringProductFacadeImp.createCateringProduct(new CateringProductRequest());
        verify(cateringProductService, times(1)).create(any(CateringProductEntity.class));
    }

    @Test
    public void updateCateringProductTest() {
        final CateringProductEntity entity = getCateringProductEntity();
        when(cateringProductService.findById(any(String.class))).thenReturn(Mono.just(entity));
        cateringProductFacadeImp.updateCateringProduct("4353", new CateringProductRequest());
        verify(cateringProductService, times(1)).findById(any(String.class));
    }
}
