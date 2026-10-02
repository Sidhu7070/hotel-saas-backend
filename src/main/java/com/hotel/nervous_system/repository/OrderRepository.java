package com.hotel.nervous_system.repository;

import com.hotel.nervous_system.model.OrderTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderTicket, String> {
    // NEW: We now require the hotelId to fetch kitchen orders and cashier bills!
    List<OrderTicket> findByHotelIdAndStatus(String hotelId, String status);
    List<OrderTicket> findByHotelIdAndTableNumberAndStatusNot(String hotelId, int tableNumber, String status);
}