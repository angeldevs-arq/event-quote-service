package com.angeldevs.eventquoteservice.planning.interfaces.rest.transform;

import com.angeldevs.eventquoteservice.planning.domain.model.commands.CreateServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.CreateServiceItemResource;

public class CreateServiceItemCommandFromResourceAssembler {

    public static CreateServiceItemCommand toCommandFromResource(CreateServiceItemResource resource){
        return new CreateServiceItemCommand(resource.description(), resource.quantity(),resource.unitPrice(),resource.totalPrice(),new QuoteId(resource.quoteId()));
    }
}
