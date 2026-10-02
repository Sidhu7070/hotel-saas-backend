package com.hotel.nervous_system.controller;

import com.hotel.nervous_system.model.MenuItem;
import com.hotel.nervous_system.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuController {

    @Autowired
    private MenuItemRepository menuRepository;

    @GetMapping("/hotel/{hotelId}")
    public List<MenuItem> getMenuByHotel(@PathVariable String hotelId) {
        return menuRepository.findByHotelId(hotelId);
    }

    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem item) {
        return menuRepository.save(item);
    }

    // NEW: API to Edit an existing dish
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable String id, @RequestBody MenuItem updatedItem) {
        return menuRepository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setDescription(updatedItem.getDescription());
            item.setPrice(updatedItem.getPrice());
            item.setCategory(updatedItem.getCategory());
            item.setImageUrl(updatedItem.getImageUrl());
            item.setAvailable(updatedItem.isAvailable());
            return menuRepository.save(item);
        }).orElse(null);
    }
}