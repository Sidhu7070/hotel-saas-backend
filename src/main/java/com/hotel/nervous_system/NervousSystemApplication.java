package com.hotel.nervous_system;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class NervousSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NervousSystemApplication.class, args);
    }

    // 1. Force the exact Cloud URI connection
    @Bean
    public MongoClient mongoClient() {
        System.out.println("--- BYPASSING AUTO-CONFIG: FORCING MONGODB CLOUD CONNECTION ---");
        return MongoClients.create("mongodb+srv://hotel_admin:4FlMVa54rc5Rpfx7@cluster0.jr063ea.mongodb.net/hotel_saas_db?appName=Cluster0");
    }

    // 2. Force the exact Database Name
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "hotel_saas_db");
    }
}