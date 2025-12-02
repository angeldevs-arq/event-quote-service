package com.angeldevs.eventquoteservice.planning.interfaces.rest.transform;

import com.angeldevs.eventquoteservice.planning.domain.model.commands.CreateQuoteCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.EventType;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteStatus;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.CreateQuoteResource;

public class CreateQuoteCommandFromResourceAssembler {

    public static CreateQuoteCommand toCommandFromResource(CreateQuoteResource resource){
        return new CreateQuoteCommand(resource.title(), resource.customerName(),EventType.valueOf(resource.eventType()),resource.guestQuantity(),resource.location(),resource.totalPrice(), QuoteStatus.valueOf(resource.state()),resource.eventDate(),resource.organizerId(), resource.hostId());
    }
}
