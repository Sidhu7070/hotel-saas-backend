package com.hotel.nervous_system.controller;

import com.hotel.nervous_system.model.OrderTicket;
import com.hotel.nervous_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // KITCHEN APP: Fetch all pending orders for THIS hotel
    @GetMapping("/hotel/{hotelId}/active")
    public List<OrderTicket> getActiveOrders(@PathVariable String hotelId) {
        return orderRepository.findByHotelIdAndStatus(hotelId, "PENDING");
    }

    // CUSTOMER APP: Send a new order to the kitchen
    @PostMapping
    public OrderTicket createOrder(@RequestBody OrderTicket order) {
        order.setOrderId("ORD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    // KITCHEN APP: Mark an order as completed
    @PutMapping("/{id}/complete")
    public OrderTicket markOrderComplete(@PathVariable String id) {
        OrderTicket order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus("COMPLETED");
        return orderRepository.save(order);
    }

    // CASHIER APP: Get all unpaid orders for a specific table in THIS hotel
    @GetMapping("/hotel/{hotelId}/table/{tableNumber}")
    public List<OrderTicket> getTableOrders(@PathVariable String hotelId, @PathVariable int tableNumber) {
        return orderRepository.findByHotelIdAndTableNumberAndStatusNot(hotelId, tableNumber, "PAID");
    }

    // CASHIER APP: Mark all orders for a table as PAID
    @PutMapping("/hotel/{hotelId}/table/{tableNumber}/pay")
    public String payTableOrders(@PathVariable String hotelId, @PathVariable int tableNumber) {
        List<OrderTicket> orders = orderRepository.findByHotelIdAndTableNumberAndStatusNot(hotelId, tableNumber, "PAID");

        for (OrderTicket order : orders) {
            order.setStatus("PAID");
        }
        orderRepository.saveAll(orders);

        return "Table " + tableNumber + " bills marked as PAID.";
    }
}