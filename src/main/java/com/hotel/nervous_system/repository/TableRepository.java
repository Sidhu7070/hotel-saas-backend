package com.hotel.nervous_system.repository;

import com.hotel.nervous_system.model.RestaurantTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends MongoRepository<RestaurantTable, String> {
    // NEW: We now require the hotelId to find tables!
    List<RestaurantTable> findByHotelId(String hotelId);
    Optional<RestaurantTable> findByHotelIdAndTableNumber(String hotelId, int tableNumber);
}