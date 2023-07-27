//package com.erp.blang;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoDatabase;
//
//@Configuration
//public class MongoConfig {
//  //  @Value("${spring.data.mongodb.database}")
//    private String databaseName="myDatabase";
//
//    @Value("${spring.data.mongodb.host}")
//    private String host;
//
//    @Value("${spring.data.mongodb.port}")
//    private int port;
//
//    @Value("${spring.data.mongodb.username}")
//    private String username;
//
//    @Value("${spring.data.mongodb.password}")
//    private String password;
//
//    @Bean
//    public MongoDatabase mongoDatabase() {
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .applyToClusterSettings(builder ->
//                        builder.hosts(Arrays.asList(new ServerAddress(host, port))))
//                .credential(MongoCredential.createCredential(username, databaseName, password.toCharArray()))
//                .build();
//
//        MongoClient mongoClient = MongoClients.create(mongoClientSettings);
//        return mongoClient.getDatabase(databaseName);
//    }
//}
