package de.caterwings.catering.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
@ConfigurationProperties(prefix = "mongo")
public class MongoDbReactivePropConfig {

    @NotEmpty
    private String host;

    @NotEmpty
    private String port;

    private String user;

    private String password;

    @NotEmpty
    private String database;

}
