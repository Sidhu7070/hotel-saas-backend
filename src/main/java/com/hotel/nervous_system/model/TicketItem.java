package com.hotel.nervous_system.model;

import lombok.Data;

@Data
public class TicketItem {
    private String name;
    private int quantity;
    private String modifiers;
}