package com.angeldevs.eventquoteservice.planning.interfaces.rest.transform;

import com.angeldevs.eventquoteservice.planning.domain.model.commands.UpdateServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.ServiceItemId;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.UpdateServiceItemResource;

public class UpdateServiceItemCommandFromResourceAssembler {
    public static UpdateServiceItemCommand toCommandFromResource(String serviceItemId,UpdateServiceItemResource resource){
        return new UpdateServiceItemCommand(new ServiceItemId(serviceItemId),resource.description(),resource.quantity(),resource.unitPrice(),resource.totalPrice());
    }
}
