package com.angeldevs.eventquoteservice.planning.domain.model.commands;

import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;

public record PendingQuoteCommand(QuoteId quoteId) {
    public PendingQuoteCommand{
        if(quoteId == null || quoteId.quoteId().isBlank()){
            throw new IllegalArgumentException("Quote id cannot be null or blank");
        }
    }
}
