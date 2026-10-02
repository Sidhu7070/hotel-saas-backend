package com.hotel.nervous_system.controller;

import com.hotel.nervous_system.model.RestaurantTable;
import com.hotel.nervous_system.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = "*")
public class TableController {

    @Autowired
    private TableRepository tableRepository;

    // CUSTOMER & CASHIER: Get tables for a specific hotel
    @GetMapping("/hotel/{hotelId}")
    public List<RestaurantTable> getHotelTables(@PathVariable String hotelId) {
        return tableRepository.findByHotelId(hotelId);
    }

    // OWNER DASHBOARD: Add a new table dynamically
    @PostMapping
    public RestaurantTable createTable(@RequestBody RestaurantTable table) {
        return tableRepository.save(table);
    }

    // CUSTOMER & CASHIER: Update a table's status
    @PutMapping("/hotel/{hotelId}/{tableNumber}/status")
    public RestaurantTable updateTableStatus(@PathVariable String hotelId, @PathVariable int tableNumber, @RequestParam String status) {
        Optional<RestaurantTable> tableOptional = tableRepository.findByHotelIdAndTableNumber(hotelId, tableNumber);

        if (tableOptional.isPresent()) {
            RestaurantTable table = tableOptional.get();
            table.setStatus(status.toUpperCase());
            return tableRepository.save(table);
        }
        throw new RuntimeException("Table not found");
    }

    // OWNER DASHBOARD: Delete a table
    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable String id) {
        tableRepository.deleteById(id);
    }
}