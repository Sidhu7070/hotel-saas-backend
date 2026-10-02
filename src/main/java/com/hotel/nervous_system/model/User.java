package com.hotel.nervous_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password; // We will encrypt this later
    private String role; // "OWNER", "CASHIER", "KITCHEN"
    private String hotelId; // Links this staff member to a specific hotel
}