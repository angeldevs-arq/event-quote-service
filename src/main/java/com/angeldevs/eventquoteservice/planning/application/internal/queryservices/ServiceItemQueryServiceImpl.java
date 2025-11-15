package com.angeldevs.eventquoteservice.planning.application.internal.queryservices;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.ServiceItem;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllServiceItemsByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetServiceItemByIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.services.ServiceItemQueryService;
import com.angeldevs.eventquoteservice.planning.infrastructure.persistence.jpa.repositories.ServiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceItemQueryServiceImpl implements ServiceItemQueryService {
    private final ServiceItemRepository serviceItemRepository;

    public ServiceItemQueryServiceImpl(ServiceItemRepository serviceItemRepository) {
        this.serviceItemRepository = serviceItemRepository;
    }

    @Override
    public List<ServiceItem> handle(GetAllServiceItemsByQuoteIdQuery query){
        return serviceItemRepository.findAllByQuoteId(query.quoteId());
    }

    @Override
    public Optional<ServiceItem> handle(GetServiceItemByIdQuery query){
        return serviceItemRepository.findByServiceItemId(query.serviceItemId());
    }
}
