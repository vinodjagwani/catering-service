package de.caterwings.catering.service;

import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface ImageUploadService {

    Mono<String> uploadFromStream(final Mono<FilePart> fileParts);

    Mono<ReactiveGridFsResource> downloadFromStream(final String objectId);

}
