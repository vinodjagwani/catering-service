package de.caterwings.catering.facade.impl;

import de.caterwings.catering.facade.ImageUploadFacade;
import de.caterwings.catering.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageUploadFacadeImpl implements ImageUploadFacade {

    private final ImageUploadService imageUploadService;


    @Override
    public Mono<String> cateringProductImageUpload(final Mono<FilePart> fileParts) {
        return imageUploadService.uploadFromStream(fileParts);
    }

    @Override
    public Mono<ReactiveGridFsResource> cateringProductImageDownload(final String objectId) {
        return imageUploadService.downloadFromStream(objectId);
    }
}
