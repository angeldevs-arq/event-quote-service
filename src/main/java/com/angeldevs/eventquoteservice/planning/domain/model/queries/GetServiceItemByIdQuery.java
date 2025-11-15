package com.angeldevs.eventquoteservice.planning.domain.model.queries;

import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.ServiceItemId;

public record GetServiceItemByIdQuery(ServiceItemId serviceItemId) {
    public GetServiceItemByIdQuery {
        if(serviceItemId == null){
            throw new IllegalArgumentException("serviceItemId cannot be null");
        }
    }
}
