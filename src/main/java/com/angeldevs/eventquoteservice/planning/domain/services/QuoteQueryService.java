package com.angeldevs.eventquoteservice.planning.domain.services;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.Quote;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.ExistsByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllQuotesByHostIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllQuotesByOrganizerIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetQuoteByQuoteIdQuery;

import java.util.List;
import java.util.Optional;

public interface QuoteQueryService {

    List<Quote> handle(GetAllQuotesByOrganizerIdQuery query);

    List<Quote> handle(GetAllQuotesByHostIdQuery query);

    Optional<Quote> handle(GetQuoteByQuoteIdQuery query);

    boolean handle(ExistsByQuoteIdQuery query);
}
