package com.angeldevs.eventquoteservice.planning.interfaces.rest.resources;

public record CreateServiceItemResource(String description, int quantity, double unitPrice, double totalPrice, String quoteId) {
    public CreateServiceItemResource{
        if(description == null || description.isBlank()){
            throw new IllegalArgumentException("Description cannot be null or empty");
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
        if(quoteId == null || quoteId.isBlank()){
            throw new IllegalArgumentException("QuoteId cannot be null or empty");
        }
    }
}
