package com.hotel.nervous_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NervousSystemApplication {

    public static void main(String[] args) {
        // FORCE the database connection at the highest system level before the app even starts
        System.setProperty("spring.data.mongodb.uri", "mongodb+srv://hotel_admin:4FlMVa54rc5Rpfx7@cluster0.jr063ea.mongodb.net/hotel_saas_db?appName=Cluster0");

        SpringApplication.run(NervousSystemApplication.class, args);
    }
}