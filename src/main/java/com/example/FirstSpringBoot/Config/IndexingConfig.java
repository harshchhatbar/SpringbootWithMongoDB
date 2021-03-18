package com.example.FirstSpringBoot.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public abstract class IndexingConfig extends AbstractMongoClientConfiguration {
    @Override
    public boolean autoIndexCreation() {
        return true;
    }
}
