package de.caterwings.catering.facade.impl;

import de.caterwings.catering.AbstractCateringTest;
import de.caterwings.catering.service.ImageUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImageUploadFacadeImplTest extends AbstractCateringTest {


    @Mock
    private ImageUploadService imageUploadService;


    @InjectMocks
    private ImageUploadFacadeImpl imageUploadFacade;


    @Test
    public void cateringProductImageUploadTest() {
        when(imageUploadService.uploadFromStream(any(Mono.class))).thenReturn(Mono.empty());
        imageUploadFacade.cateringProductImageUpload(Mono.just(new FilePart() {
            @Override
            public String filename() {
                return "test.file";
            }

            @Override
            public Mono<Void> transferTo(Path path) {
                return Mono.empty();
            }

            @Override
            public String name() {
                return "test.file";
            }

            @Override
            public HttpHeaders headers() {
                return HttpHeaders.EMPTY;
            }

            @Override
            public Flux<DataBuffer> content() {
                return DataBufferUtils.readInputStream(
                        () -> getImageResource().getInputStream(), new DefaultDataBufferFactory(true), 3);
            }
        }));
        verify(imageUploadService, times(1)).uploadFromStream(any(Mono.class));
    }

    @Test
    public void cateringProductImageDownloadTest() {
        when(imageUploadService.downloadFromStream(any(String.class))).thenReturn(Mono.empty());
        imageUploadFacade.cateringProductImageDownload("5d001e4b132eee34d87b3974");
        verify(imageUploadService, times(1)).downloadFromStream(any(String.class));
    }
}
