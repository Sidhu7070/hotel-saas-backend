package com.hotel.nervous_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "menu_items")
public class MenuItem {
    @Id
    private String id;
    private String hotelId;
    private String name;
    private String description;
    private double price;
    private String category;
    private boolean isAvailable;
    private String imageUrl; // NEW: Allows the owner to add a dish image
}