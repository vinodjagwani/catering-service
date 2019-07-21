package de.caterwings.catering.controller;

import de.caterwings.catering.dto.CateringProductRequest;
import de.caterwings.catering.dto.CateringProductResponse;
import de.caterwings.catering.entity.CateringProductEntity;
import de.caterwings.catering.facade.CateringProductFacade;
import de.caterwings.catering.validation.annotation.QueryParamPredicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/catering")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CateringProductController {


    private final CateringProductFacade cateringProductFacade;


    @QueryParamPredicate(validParams = {"id", "title", "description"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CateringProductResponse> findAllCateringProducts(@RequestParam Map<String, Object> params, final CateringProductEntity entity) {
        return cateringProductFacade.findAllCateringProducts(Example.of(entity)).log();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CateringProductResponse>> createCateringProduct(@Valid @RequestBody final CateringProductRequest request) {
        return cateringProductFacade.createCateringProduct(request).map(p -> new ResponseEntity<>(p, HttpStatus.CREATED)).log();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CateringProductResponse>> updateCateringProduct(@PathVariable final String id, @Valid @RequestBody final CateringProductRequest request) {
        return cateringProductFacade.updateCateringProduct(id, request)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND)).log();
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCateringProduct(@PathVariable final String id) {
        return cateringProductFacade.deleteCateringProduct(id).log().then(Mono.just(new ResponseEntity<>(HttpStatus.OK)));
    }


}
