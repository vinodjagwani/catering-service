package de.caterwings.catering.service.impl;

import de.caterwings.catering.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageUploadServiceImpl implements ImageUploadService {

    private final ReactiveGridFsTemplate gridFsTemplate;

    @Override
    public Mono<ReactiveGridFsResource> downloadFromStream(final String objectId) {
        return gridFsTemplate.findOne(query(where("_id").is(objectId))).log().flatMap(gridFsTemplate::getResource);
    }

    @Override
    public Mono<String> uploadFromStream(final Mono<FilePart> fileParts) {
        return fileParts.flatMap(parts -> gridFsTemplate.store(parts.content(), parts.filename())).map(ObjectId::toHexString);
    }
}
