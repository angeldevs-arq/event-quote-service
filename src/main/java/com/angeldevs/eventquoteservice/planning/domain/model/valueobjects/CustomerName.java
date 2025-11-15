package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;


public record CustomerName(String customerName) {
    /**
     * Constructor for CustomerName.
     *
     * @param customerName the name of the customer
     * @throws IllegalArgumentException if the customerName is null or empty
     */
    public CustomerName {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer Name cannot be null or empty");
        }
    }
}
