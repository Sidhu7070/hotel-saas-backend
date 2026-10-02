package com.hotel.nervous_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "tables")
public class RestaurantTable {
    @Id
    private String id;
    private String hotelId; // NEW: Link table to a specific hotel
    private int tableNumber;
    private int capacity;
    private String status;
}