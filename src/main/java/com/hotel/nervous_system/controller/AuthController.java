package com.hotel.nervous_system.controller;

import com.hotel.nervous_system.model.Hotel;
import com.hotel.nervous_system.model.User;
import com.hotel.nervous_system.repository.HotelRepository;
import com.hotel.nervous_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/register-owner")
    public Map<String, Object> registerOwner(@RequestBody Map<String, String> payload) {
        Map<String, Object> response = new HashMap<>();
        if (userRepository.findByUsername(payload.get("username")).isPresent()) {
            response.put("error", "Username already exists");
            return response;
        }
        Hotel hotel = new Hotel();
        hotel.setName(payload.get("hotelName"));
        hotel.setAddress(payload.get("hotelAddress"));
        Hotel savedHotel = hotelRepository.save(hotel);

        User user = new User();
        user.setUsername(payload.get("username"));
        user.setPassword(payload.get("password"));
        user.setRole("OWNER");
        user.setHotelId(savedHotel.getId());
        userRepository.save(user);

        response.put("message", "Owner and Hotel registered successfully!");
        response.put("hotelId", savedHotel.getId());
        response.put("role", "OWNER");
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> payload) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> userOpt = userRepository.findByUsername(payload.get("username"));

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(payload.get("password"))) {
            User user = userOpt.get();
            response.put("message", "Login successful");
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("hotelId", user.getHotelId());
        } else {
            response.put("error", "Invalid username or password");
        }
        return response;
    }

    // --- NEW: OWNER DASHBOARD - Add Staff ---
    @PostMapping("/register-staff")
    public Map<String, Object> registerStaff(@RequestBody Map<String, String> payload) {
        Map<String, Object> response = new HashMap<>();
        if (userRepository.findByUsername(payload.get("username")).isPresent()) {
            response.put("error", "Username already exists");
            return response;
        }
        User user = new User();
        user.setUsername(payload.get("username"));
        user.setPassword(payload.get("password"));
        user.setRole(payload.get("role").toUpperCase()); // KITCHEN or CASHIER
        user.setHotelId(payload.get("hotelId"));
        userRepository.save(user);

        response.put("message", "Staff created successfully!");
        return response;
    }

    // --- NEW: OWNER DASHBOARD - View Staff ---
    @GetMapping("/staff/{hotelId}")
    public List<User> getStaff(@PathVariable String hotelId) {
        return userRepository.findByHotelId(hotelId);
    }

    // NEW: API to Edit Staff
    @PutMapping("/staff/{id}")
    public Map<String, Object> updateStaff(@PathVariable String id, @RequestBody Map<String, String> payload) {
        Map<String, Object> response = new java.util.HashMap<>();
        java.util.Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Update password only if they typed a new one
            if (payload.containsKey("password") && !payload.get("password").isEmpty()) {
                user.setPassword(payload.get("password"));
            }
            if (payload.containsKey("role")) {
                user.setRole(payload.get("role").toUpperCase());
            }
            userRepository.save(user);
            response.put("message", "Staff updated successfully");
        } else {
            response.put("error", "User not found");
        }
        return response;
    }
}