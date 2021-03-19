package com.example.FirstSpringBoot.Config;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBconfig extends AbstractMongoClientConfiguration {
    @Bean
    public MongoClient mongoClient()
    {
        return MongoClients.create("mongodb+srv://m220student:m220passwordharsh@mflix.gd3j5.mongodb.net");
    }
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "sample_mflix");
    }

    @Override
    protected String getDatabaseName() {
        return "sample_mflix";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}