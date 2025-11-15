package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable

public enum StatusType {
    ACTIVE,
    TO_CONFIRM,
    CANCELLED,
    COMPLETED
}
