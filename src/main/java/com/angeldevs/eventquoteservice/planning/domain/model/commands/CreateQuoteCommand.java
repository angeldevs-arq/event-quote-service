package com.angeldevs.eventquoteservice.planning.domain.model.commands;

import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.EventType;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteStatus;

import java.util.Date;

public record CreateQuoteCommand(String title, EventType eventType, int guestQuantity, String location, double totalPrice, QuoteStatus state, Date eventDate, Long organizerId, Long hostId) {
    public CreateQuoteCommand{
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if(eventType == null){
            throw new IllegalArgumentException("EventType cannot be null");
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
        if(state == null){
            throw new IllegalArgumentException("State cannot be null");
        }
        if(eventDate == null){
            throw new IllegalArgumentException("Event date cannot be null");
        }
        if(organizerId <= 0){
            throw new IllegalArgumentException("Organizer id cannot be negative");
        }
        if(hostId <= 0){
            throw new IllegalArgumentException("Host id cannot be negative");
        }
    }
}
