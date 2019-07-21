package de.caterwings.catering.controller;


import de.caterwings.catering.AbstractCateringTest;
import de.caterwings.catering.constant.ErrorCodeEnum;
import de.caterwings.catering.dto.CateringProductRequest;
import de.caterwings.catering.dto.CateringProductResponse;
import de.caterwings.catering.entity.CateringProductEntity;
import de.caterwings.catering.exception.CateringServiceException;
import de.caterwings.catering.facade.CateringProductFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;


@RunWith(SpringRunner.class)
@WebFluxTest(controllers = CateringProductController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class CateringProductControllerTest extends AbstractCateringTest {


    private static final String CATERING_URI = "/api/v1/catering";

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private CateringProductFacade cateringProductFacade;


    @Test
    public void findAllCateringProductsTest() {
        when(cateringProductFacade.findAllCateringProducts(any(Example.class))).thenReturn(Flux.just(getCateringProductEntity()));
        WebTestClient.BodySpec<CateringProductEntity[], ?> bodySpec = webClient.get().uri(CATERING_URI)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(CateringProductEntity[].class).consumeWith(document("get-all-catering-products",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestParameters(parameterWithName("id").description("id").optional(),
                                parameterWithName("title").description("title").optional(),
                                parameterWithName("description").description("description").optional()),
                        responseFields(
                                fieldWithPath("[].id").description("id"),
                                fieldWithPath("[].vendorUid").description("vendorUid"),
                                fieldWithPath("[].title").description("title"),
                                fieldWithPath("[].description").description("description"),
                                fieldWithPath("[].price").description("price"),
                                fieldWithPath("[].image").description("image"),
                                fieldWithPath("[].dietaryFlag").description("dietaryFlag"),
                                fieldWithPath("[].views").description("views"))));
        bodySpec.isEqualTo(new CateringProductEntity[]{getCateringProductEntity()});
    }

    @Test
    public void findAllCateringProductsTes_search_title() {
        when(cateringProductFacade.findAllCateringProducts(any(Example.class))).thenReturn(Flux.just(getCateringProductEntity()));
        WebTestClient.BodySpec<CateringProductEntity[], ?> bodySpec = webClient.get().uri(String.format("%s?title=%s", CATERING_URI, "sf"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(CateringProductEntity[].class).consumeWith(document("get-all-catering-products_search_title",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].id").description("id"),
                                fieldWithPath("[].vendorUid").description("vendorUid"),
                                fieldWithPath("[].title").description("title"),
                                fieldWithPath("[].description").description("description"),
                                fieldWithPath("[].price").description("price"),
                                fieldWithPath("[].image").description("image"),
                                fieldWithPath("[].dietaryFlag").description("dietaryFlag"),
                                fieldWithPath("[].views").description("views"))));
        bodySpec.isEqualTo(new CateringProductEntity[]{getCateringProductEntity()});
    }

    @Test
    public void createCateringProductTest() {
        final CateringProductResponse response = getCateringProductResponse();
        when(cateringProductFacade.createCateringProduct(any(CateringProductRequest.class))).thenReturn(Mono.just(response));
        WebTestClient.BodySpec<CateringProductEntity, ?> bodySpec = webClient.post().uri(CATERING_URI)
                .body(BodyInserters.fromObject(response))
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(CateringProductEntity.class).consumeWith(document("create-catering-product",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("id").description("id"),
                                fieldWithPath("vendorUid").description("vendorUid"),
                                fieldWithPath("title").description("title"),
                                fieldWithPath("description").description("description"),
                                fieldWithPath("price").description("price"),
                                fieldWithPath("image").description("image"),
                                fieldWithPath("dietaryFlag").description("dietaryFlag"),
                                fieldWithPath("views").description("views")),
                        responseFields(
                                fieldWithPath("id").description("id"),
                                fieldWithPath("vendorUid").description("vendorUid"),
                                fieldWithPath("title").description("title"),
                                fieldWithPath("description").description("description"),
                                fieldWithPath("price").description("price"),
                                fieldWithPath("image").description("image"),
                                fieldWithPath("dietaryFlag").description("dietaryFlag"),
                                fieldWithPath("views").description("views"))));
        bodySpec.isEqualTo(getCateringProductEntity());
    }

    @Test
    public void updateCateringProductTest() {
        final CateringProductResponse response = getCateringProductResponse();
        when(cateringProductFacade.updateCateringProduct(any(String.class), any(CateringProductRequest.class))).thenReturn(Mono.just(response));
        WebTestClient.BodySpec<CateringProductEntity, ?> bodySpec = webClient.put().uri(CATERING_URI + "/{id}", "5cfe299a9e6c8057c12dde64")
                .body(BodyInserters.fromObject(getCateringProductRequest()))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(CateringProductEntity.class).consumeWith(document("update-catering-product",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("id").description("id")),
                        requestFields(
                                fieldWithPath("vendorUid").description("vendorUid"),
                                fieldWithPath("title").description("title"),
                                fieldWithPath("description").description("description"),
                                fieldWithPath("price").description("price"),
                                fieldWithPath("image").description("image"),
                                fieldWithPath("dietaryFlag").description("dietaryFlag"),
                                fieldWithPath("views").description("views")),
                        responseFields(
                                fieldWithPath("id").description("id"),
                                fieldWithPath("vendorUid").description("vendorUid"),
                                fieldWithPath("title").description("title"),
                                fieldWithPath("description").description("description"),
                                fieldWithPath("price").description("price"),
                                fieldWithPath("image").description("image"),
                                fieldWithPath("dietaryFlag").description("dietaryFlag"),
                                fieldWithPath("views").description("views"))));
        bodySpec.isEqualTo(getCateringProductEntity());
    }

    @Test
    public void updateCateringProductTest_not_found() {
        when(cateringProductFacade.updateCateringProduct(any(String.class), any(CateringProductRequest.class))).thenReturn(Mono.empty());
        webClient.put().uri(CATERING_URI + "/{id}", "5cfe299a9e6c8057c12dde64")
                .body(BodyInserters.fromObject(getCateringProductRequest()))
                .exchange()
                .expectStatus().isNotFound().expectBody(Object.class);
    }

    @Test
    public void deleteCateringProductTest() {
        when(cateringProductFacade.deleteCateringProduct(any(String.class))).thenReturn(Mono.empty());
        webClient.delete().uri(CATERING_URI + "/{id}", "5cfe299a9e6c8057c12dde64").exchange().expectStatus()
                .isOk().expectBody(Object.class).consumeWith(document("delete-catering-product",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                pathParameters(parameterWithName("id").description("id"))));
    }

    @Test
    public void deleteCateringProductTest_not_found() {
        when(cateringProductFacade.deleteCateringProduct(any(String.class)))
                .thenThrow(new CateringServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "product not found"));
        webClient.delete().uri(CATERING_URI + "/{id}", "5cfe299a9e6c8057c12dde64").exchange().expectStatus().isBadRequest()
                .expectBody(Object.class).consumeWith(document("delete-catering-product_not_found",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                pathParameters(parameterWithName("id").description("id"))));
    }

}
