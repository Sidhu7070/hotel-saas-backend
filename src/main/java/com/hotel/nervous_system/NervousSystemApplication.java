package com.hotel.nervous_system;

import com.hotel.nervous_system.model.RestaurantTable;
import com.hotel.nervous_system.repository.TableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NervousSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NervousSystemApplication.class, args);
    }

    // This script runs automatically when the server starts
//    @Bean
//    CommandLineRunner initDatabase(TableRepository repository) {
//        return args -> {
//            // Only add tables if the database is currently empty
//            if (repository.count() == 0) {
//                System.out.println("Database is empty. Seeding 12 tables...");
//
//                for (int i = 1; i <= 12; i++) {
//                    RestaurantTable table = new RestaurantTable();
//                    table.setTableNumber(i);
//                    table.setCapacity((i % 3 == 0) ? 6 : 4);
//                    table.setStatus("AVAILABLE");
//                    repository.save(table);
//                }
//
//                System.out.println("12 Tables successfully saved to MongoDB Cloud!");
//            } else {
//                System.out.println("Tables already exist in the database. Skipping setup.");
//            }
//        };
//    }
}