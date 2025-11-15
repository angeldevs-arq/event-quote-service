package com.angeldevs.eventquoteservice.planning.domain.services;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.ServiceItem;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.CreateServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.DeleteServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.UpdateServiceItemCommand;

import java.util.Optional;

public interface ServiceItemCommandService {
    String handle(CreateServiceItemCommand command);
    Optional<ServiceItem> handle(UpdateServiceItemCommand command);
    void handle(DeleteServiceItemCommand command);
}
