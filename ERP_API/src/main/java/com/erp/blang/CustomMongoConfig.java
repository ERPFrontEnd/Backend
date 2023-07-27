package com.erp.blang;

import java.util.Collection;
import java.util.Collections;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
//@EnableMongoRepositories(basePackages = "com.erp.blang.repository") // Replace with your repository package
public class CustomMongoConfig extends AbstractMongoClientConfiguration {

//    @Value("${spring.data.mongodb.uri}")
    private String mongoUri="mongodb+srv://Blang:root@cluster1.79hgdk8.mongodb.net/Blang?retryWrites=true&w=majority";

    @Override
    protected String getDatabaseName() {
        // Return the name of the database you want to connect to
        // It will be extracted from the MongoDB URI automatically
        return "Blang";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        return MongoClients.create(connectionString);
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        // Return the package where your MongoDB entities (POJOs) are located
        // They will be scanned for @Document annotations
        return Collections.singleton("com.erp.blang.model"); // Replace with your entity package
    }
}
