package com.angeldevs.eventquoteservice.planning.interfaces.rest.resources;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record UpdateQuoteResource(String title,String customerName, String eventType, int guestQuantity, String location, double totalPrice, @DateTimeFormat(iso =DateTimeFormat.ISO.DATE_TIME ) Date eventDate) {
    public UpdateQuoteResource{
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if(customerName == null || customerName.isBlank()){
            throw new IllegalArgumentException("Customer Name cannot be null or blank");
        }
        if(eventType == null || eventType.isBlank()){
            throw new IllegalArgumentException("EventType cannot be null or blank");
        }
        if(guestQuantity <= 0){
            throw new IllegalArgumentException("Guest quantity cannot be negative");
        }
        if(location == null || location.isBlank()){
            throw new IllegalArgumentException("Location cannot be null or blank");
        }
        if(totalPrice <= 0){
            throw new IllegalArgumentException("Total price cannot be negative");
        }
        if(eventDate == null){
            throw new IllegalArgumentException("Event date cannot be null");
        }
    }
}
