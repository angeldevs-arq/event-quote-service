package com.angeldevs.eventquoteservice.planning.infrastructure.persistence.jpa.repositories;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.ServiceItem;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.ServiceItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, ServiceItemId> {
    List<ServiceItem> findAllByQuoteId(QuoteId quoteId);
    boolean existsByServiceItemIdAndQuoteId(ServiceItemId serviceItemId, QuoteId quoteId);
    Optional<ServiceItem> findByServiceItemId(ServiceItemId serviceItemId);
}
