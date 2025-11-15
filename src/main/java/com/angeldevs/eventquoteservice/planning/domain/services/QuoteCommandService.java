package com.angeldevs.eventquoteservice.planning.domain.services;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.Quote;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.*;

import java.util.Optional;

public interface QuoteCommandService {

    String handle(CreateQuoteCommand command);
    Optional<Quote> handle(UpdateQuoteCommand command);
    void handle(DeleteQuoteCommand command);

    String handle(ConfirmQuoteCommand command);
    String handle(RejectQuoteCommand command);
}
