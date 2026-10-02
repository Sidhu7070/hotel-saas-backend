package com.hotel.nervous_system.controller;

import com.hotel.nervous_system.model.Hotel;
import com.hotel.nervous_system.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    // CUSTOMER APP: Fetch all hotels so the customer can pick one
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}