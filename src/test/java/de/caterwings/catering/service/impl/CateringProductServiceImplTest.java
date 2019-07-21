package de.caterwings.catering.service.impl;


import de.caterwings.catering.AbstractCateringTest;
import de.caterwings.catering.entity.CateringProductEntity;
import de.caterwings.catering.repository.CateringProductReactiveRepository;
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
public class CateringProductServiceImplTest extends AbstractCateringTest {


    @Mock
    private CateringProductReactiveRepository cateringProductReactiveRepository;


    @InjectMocks
    private CateringProductServiceImpl cateringProductServiceImpl;


    @Test
    public void findByIdTest() {
        when(cateringProductReactiveRepository.findById(any(String.class))).thenReturn(Mono.just(getCateringProductEntity()));
        cateringProductServiceImpl.findById("324234");
        verify(cateringProductReactiveRepository, times(1)).findById(any(String.class));
    }

    @Test
    public void deleteTest() {
        when(cateringProductReactiveRepository.delete(any(CateringProductEntity.class))).thenReturn(Mono.empty());
        cateringProductServiceImpl.delete(getCateringProductEntity());
        verify(cateringProductReactiveRepository, times(1)).delete(any(CateringProductEntity.class));
    }

    @Test
    public void findAllTest() {
        when(cateringProductReactiveRepository.findAll(any(Example.class))).thenReturn(Flux.just(getCateringProductEntity()));
        cateringProductServiceImpl.findAll(Example.of(getCateringProductEntity()));
        verify(cateringProductReactiveRepository, times(1)).findAll(any(Example.class));
    }

    @Test
    public void createTest() {
        final CateringProductEntity entity = getCateringProductEntity();
        when(cateringProductReactiveRepository.save(any(CateringProductEntity.class))).thenReturn(Mono.just(entity));
        cateringProductServiceImpl.create(entity);
        verify(cateringProductReactiveRepository, times(1)).save(any(CateringProductEntity.class));
    }

}
