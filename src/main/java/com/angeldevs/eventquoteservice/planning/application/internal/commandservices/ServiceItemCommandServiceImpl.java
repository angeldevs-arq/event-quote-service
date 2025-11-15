package com.angeldevs.eventquoteservice.planning.application.internal.commandservices;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.ServiceItem;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.CreateServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.DeleteServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.UpdateServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.ServiceItemId;
import com.angeldevs.eventquoteservice.planning.domain.services.ServiceItemCommandService;
import com.angeldevs.eventquoteservice.planning.infrastructure.persistence.jpa.repositories.QuoteRepository;
import com.angeldevs.eventquoteservice.planning.infrastructure.persistence.jpa.repositories.ServiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceItemCommandServiceImpl implements ServiceItemCommandService {
    private final ServiceItemRepository serviceItemRepository;
    private final QuoteRepository quoteRepository;

    public ServiceItemCommandServiceImpl(ServiceItemRepository serviceItemRepository, QuoteRepository quoteRepository) {
        this.serviceItemRepository = serviceItemRepository;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public String handle(CreateServiceItemCommand command){
        // If the command provide an invalid quote id, the service item won't be created and return null
        if(!quoteRepository.existsById(command.quoteId())){
            return null;
        }
        var serviceItem = new ServiceItem(command);
        var createdServiceItem = serviceItemRepository.save(serviceItem);
        return createdServiceItem.getServiceItemId();
    }

    @Override
    public Optional<ServiceItem> handle(UpdateServiceItemCommand command){
        var serviceItem = serviceItemRepository.findById(command.serviceItemId())
                .orElseThrow(() -> new IllegalArgumentException("Service Item Not Found"));
        try{
            var updatedServiceItem = serviceItem.updateInformation(command.description(),command.quantity(), command.unitPrice(), command.totalPrice());
            serviceItemRepository.save(updatedServiceItem);
            return Optional.of(updatedServiceItem);
        }catch(Exception e){
            throw new RuntimeException("Error updating service item: " + e.getMessage(),e);
        }
    }

    @Override
    public void handle(DeleteServiceItemCommand command){
        verifyIfServiceItemExistById(command.serviceItemId());
        try {
            serviceItemRepository.deleteById(command.serviceItemId());
        }catch(Exception e){
            throw new RuntimeException("Error deleting service item: " + e.getMessage(),e);
        }
    }

    public void verifyIfServiceItemExistById(ServiceItemId serviceItemId){
        if(!serviceItemRepository.existsById(serviceItemId)){
            throw new IllegalArgumentException("Service Item with id "  + serviceItemId + " does not exist");
        }
    }
}
