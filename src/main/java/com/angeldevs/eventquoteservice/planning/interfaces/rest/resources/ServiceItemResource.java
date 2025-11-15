package com.angeldevs.eventquoteservice.planning.interfaces.rest.resources;

public record ServiceItemResource(String id, String description, int quantity, double unitPrice, double totalPrice, String quoteId) {
    public ServiceItemResource{
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("serviceItemId cannot be null or empty");
        }
        if(description == null || description.isBlank()){
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if(quantity <= 0){
            throw new IllegalArgumentException("quantity cannot be negative");
        }
        if(unitPrice <= 0){
            throw new IllegalArgumentException("unitPrice cannot be negative");
        }
        if(totalPrice <= 0){
            throw new IllegalArgumentException("totalPrice cannot be negative");
        }
        if(quoteId == null || quoteId.isBlank()){
            throw new IllegalArgumentException("quoteId cannot be null or empty");
        }
    }
}
