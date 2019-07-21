package de.caterwings.catering.config;

import de.caterwings.catering.constant.CateringConstant;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
public class CateringConfig {


    @Bean(name = "dozerMapper")
    public DozerBeanMapperFactoryBean dozerMapper() throws IOException {
        DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
        final Resource[] resources = new PathMatchingResourcePatternResolver().getResources(CateringConstant.MAPPING_XML);
        mapper.setMappingFiles(resources);
        return mapper;
    }

}
