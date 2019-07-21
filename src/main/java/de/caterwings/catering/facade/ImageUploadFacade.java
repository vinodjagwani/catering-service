package de.caterwings.catering.facade;

import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface ImageUploadFacade {

    Mono<String> cateringProductImageUpload(final Mono<FilePart> fileParts);

    Mono<ReactiveGridFsResource> cateringProductImageDownload(final String objectId);
}
