package com.angeldevs.eventquoteservice.planning.domain.model.commands;

import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;

public record CreateServiceItemCommand(String description, int quantity, double unitPrice, double totalPrice, QuoteId quoteId) {
    public CreateServiceItemCommand{
        if(description == null || description.isBlank()){
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if(unitPrice <= 0){
            throw new IllegalArgumentException("UnitPrice cannot be negative");
        }
        if(totalPrice <= 0){
            throw new IllegalArgumentException("TotalPrice cannot be negative");
        }
        if(quoteId == null){
            throw new IllegalArgumentException("QuoteId cannot be null");
        }
    }
}
