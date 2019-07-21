package de.caterwings.catering.controller;


import de.caterwings.catering.Application;
import de.caterwings.catering.facade.ImageUploadFacade;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = ImageUploadController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class ImageUploadControllerTest {


    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ImageUploadFacade imageUploadFacade;


    @Test
    public void cateringProductImageUploadTest() {
        when(imageUploadFacade.cateringProductImageUpload(any(Mono.class))).thenReturn(Mono.just("5cfea543e2507016ba4d6ebd"));
        WebTestClient.BodySpec<String, ?> bodySpec = webClient.post().uri("/api/v1/catering/image/upload")
                .syncBody(generateBody())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("text/plain;charset=UTF-8")
                .expectBody(String.class).consumeWith(document("upload-image", preprocessRequest(prettyPrint())));
        Assertions.assertNotNull(bodySpec.returnResult().getResponseBody());

    }

    @Test
    public void cateringProductImageDownloadTest() {
        final ReactiveGridFsResource resource = mock(ReactiveGridFsResource.class);
        final Flux<DataBuffer> flux = DataBufferUtils.readInputStream(
                () -> getImageResource().getInputStream(), new DefaultDataBufferFactory(true), 3);
        when(resource.getDownloadStream()).thenReturn(flux);
        when(imageUploadFacade.cateringProductImageDownload(any(String.class))).thenReturn(Mono.just(resource));
        WebTestClient.BodySpec<byte[], ?> bodySpec = webClient.get().uri("/api/v1/catering/image/download/{id}", "5cfea543e2507016ba4d6ebd")
                .exchange()
                .expectStatus().isOk()
                .expectBody(byte[].class).consumeWith(document("download-image"));
        Assertions.assertNotNull(bodySpec.returnResult().getResponseBody());

    }

    private MultiValueMap<String, HttpEntity<?>> generateBody() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("fileParts", getImageResource());
        return builder.build();
    }

    private Resource getImageResource() {
        return new ClassPathResource("/image.jpeg", Application.class);
    }

}
