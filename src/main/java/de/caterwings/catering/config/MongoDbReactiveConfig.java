package de.caterwings.catering.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;


@Configuration
@EnableConfigurationProperties
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MongoDbReactiveConfig extends AbstractReactiveMongoConfiguration {


    private final MongoDbReactivePropConfig mongoDbReactivePropConfig;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return mongoDbReactivePropConfig.getDatabase();
    }

    @Bean
    @Override
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

    @Bean
    public ReactiveGridFsTemplate reactiveGridFsTemplate(final ReactiveMongoDatabaseFactory reactiveMongoDbFactory, final MappingMongoConverter mappingMongoConverter) {
        return new ReactiveGridFsTemplate(reactiveMongoDbFactory, mappingMongoConverter);
    }
}
