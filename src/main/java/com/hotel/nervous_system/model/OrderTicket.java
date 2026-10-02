package com.hotel.nervous_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
public class OrderTicket {

    @Id
    private String id;
    private String hotelId; // NEW: Links this order to a specific hotel
    private String orderId;
    private int tableNumber;
    private LocalDateTime orderTime;
    private List<TicketItem> items;
    private String status; // "PENDING" for kitchen, "COMPLETED" when done
}