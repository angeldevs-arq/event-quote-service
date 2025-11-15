package com.angeldevs.eventquoteservice.planning.application.internal.queryservices;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.Quote;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.ExistsByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllQuotesByHostIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllQuotesByOrganizerIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetQuoteByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.services.QuoteQueryService;
import com.angeldevs.eventquoteservice.planning.infrastructure.persistence.jpa.repositories.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteQueryServiceImpl implements QuoteQueryService {
    private final QuoteRepository quoteRepository;

    public QuoteQueryServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public List<Quote> handle(GetAllQuotesByOrganizerIdQuery query){
        return quoteRepository.findAllByOrganizerId(query.organizerId());
    }

    @Override
    public List<Quote> handle(GetAllQuotesByHostIdQuery query){
        return quoteRepository.findAllByHostId(query.hostId());
    }

    public Optional<Quote> handle(GetQuoteByQuoteIdQuery query){
        return quoteRepository.findByQuoteId(query.quoteId());
    }

    public boolean handle(ExistsByQuoteIdQuery query){
        return quoteRepository.existsByQuoteId(query.quoteId());
    }
}
