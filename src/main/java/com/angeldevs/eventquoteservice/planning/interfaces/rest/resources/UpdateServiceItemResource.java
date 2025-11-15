package com.angeldevs.eventquoteservice.planning.interfaces.rest.resources;

public record UpdateServiceItemResource(String description, int quantity, double unitPrice, double totalPrice) {
    public UpdateServiceItemResource {
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
    }
}
