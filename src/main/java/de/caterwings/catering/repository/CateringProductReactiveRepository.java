package de.caterwings.catering.repository;

import de.caterwings.catering.entity.CateringProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CateringProductReactiveRepository extends ReactiveMongoRepository<CateringProductEntity, String> {


}

