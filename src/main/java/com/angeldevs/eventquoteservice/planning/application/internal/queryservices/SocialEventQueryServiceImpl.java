package com.angeldevs.eventquoteservice.planning.application.internal.queryservices;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.SocialEvent;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllSocialEventQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventByStatusQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventByTitleQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventsByOrganizerQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventByIdQuery;
import java.util.Optional;
import com.angeldevs.eventquoteservice.planning.domain.services.SocialEventQueryService;
import com.angeldevs.eventquoteservice.planning.infrastructure.persistence.jpa.repositories.SocialEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of SocialEventQueryService.
 */
@Service
public class SocialEventQueryServiceImpl implements SocialEventQueryService {

    private final SocialEventRepository socialEventRepository;

    public SocialEventQueryServiceImpl(SocialEventRepository socialEventRepository) {
        this.socialEventRepository = socialEventRepository;
    }

    @Override
    public List<SocialEvent> handle(GetAllSocialEventQuery query) {
        return socialEventRepository.findAll();
    }

    @Override
    public List<SocialEvent> handle(GetSocialEventByStatusQuery query) {
        return socialEventRepository.findByStatusValueStatus(query.status());
    }

    @Override
    public List<SocialEvent> handle(GetSocialEventByTitleQuery query) {
        return socialEventRepository.findByTitleTitleContainingIgnoreCase(query.title());
    }

    @Override
    public List<SocialEvent> handle(GetSocialEventsByOrganizerQuery query) {
        return socialEventRepository.findByCustomerNameCustomerName(query.customerName());
    }

    @Override
    public Optional<SocialEvent> handle(GetSocialEventByIdQuery query) {
        return socialEventRepository.findById(query.socialEventId());
    }
}