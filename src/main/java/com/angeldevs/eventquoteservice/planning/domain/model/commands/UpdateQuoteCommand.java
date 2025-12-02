package com.angeldevs.eventquoteservice.planning.domain.model.commands;

import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.EventType;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;

import java.util.Date;

public record UpdateQuoteCommand(QuoteId quoteId, String title,String customerName, EventType eventType, int guestQuantity, String location, double totalPrice, Date eventDate) {
    public UpdateQuoteCommand{
        if(quoteId==null || quoteId.quoteId().isBlank()){
            throw new IllegalArgumentException("Quote id cannot be null");
        }
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
        if(eventDate == null){
            throw new IllegalArgumentException("Event date cannot be null");
        }
    }
}
