package de.caterwings.catering.controller;


import de.caterwings.catering.facade.ImageUploadFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/catering")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageUploadController {


    private final ImageUploadFacade imageUploadFacade;


    @PostMapping(value = "/image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<String>> cateringProductImageUpload(@RequestPart("file") final Mono<FilePart> fileParts) {
        return imageUploadFacade.cateringProductImageUpload(fileParts)
                .map(objectId -> ResponseEntity.ok().body(objectId));
    }


    @GetMapping("/image/download/{id}")
    public Flux<Void> cateringProductImageDownload(@PathVariable("id") final String id, final ServerWebExchange exchange) {
        return imageUploadFacade.cateringProductImageDownload(id)
                .flatMapMany(r -> exchange.getResponse().writeWith(r.getDownloadStream()));
    }
}


