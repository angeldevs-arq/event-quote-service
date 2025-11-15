package com.angeldevs.eventquoteservice.planning.domain.model.queries;

import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;

public record GetAllServiceItemsByQuoteIdQuery(QuoteId quoteId) {
}
