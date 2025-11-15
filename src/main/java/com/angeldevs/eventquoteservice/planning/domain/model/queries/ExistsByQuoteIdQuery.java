package com.angeldevs.eventquoteservice.planning.domain.model.queries;

import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;

public record ExistsByQuoteIdQuery(QuoteId quoteId) {
    public ExistsByQuoteIdQuery{
        if(quoteId == null){
            throw new IllegalArgumentException("quoteId cannot be null");
        }
    }
}
