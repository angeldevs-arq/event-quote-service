package com.angeldevs.eventquoteservice.planning.domain.model.commands;

import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.ServiceItemId;

public record UpdateServiceItemCommand(ServiceItemId serviceItemId, String description, int quantity, double unitPrice, double totalPrice) {
    public UpdateServiceItemCommand{
        if(serviceItemId==null || serviceItemId.serviceItemId().isBlank()){
            throw new IllegalArgumentException("Service Item Id cannot be null or empty");
        }
        if(description==null || description.isBlank()){
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if(quantity<=0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if(unitPrice<=0){
            throw new IllegalArgumentException("Unit Price cannot be negative");
        }
        if(totalPrice<=0){
            throw new IllegalArgumentException("Total Price cannot be negative");
        }
    }
}
