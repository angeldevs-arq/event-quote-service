package com.angeldevs.eventquoteservice.planning.domain.services;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.ServiceItem;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllServiceItemsByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetServiceItemByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ServiceItemQueryService {
    List<ServiceItem> handle(GetAllServiceItemsByQuoteIdQuery query);

    Optional<ServiceItem> handle(GetServiceItemByIdQuery query);
}
