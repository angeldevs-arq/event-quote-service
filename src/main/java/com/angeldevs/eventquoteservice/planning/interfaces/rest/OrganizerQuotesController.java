package com.angeldevs.eventquoteservice.planning.interfaces.rest;

//import com.eventify.platform.planning.application.internal.outboundservices.acl.PlanningExternalProfileService;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllQuotesByOrganizerIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.services.QuoteCommandService;
import com.angeldevs.eventquoteservice.planning.domain.services.QuoteQueryService;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.QuoteResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.QuoteResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/organizers/{organizerId}/quotes",produces = APPLICATION_JSON_VALUE)
@Tag(name="Organizer")
public class OrganizerQuotesController {
    private final QuoteCommandService quoteCommandService;
    private final QuoteQueryService  quoteQueryService;
    //private final PlanningExternalProfileService planningExternalProfileService;

    public OrganizerQuotesController(QuoteCommandService quoteCommandService, QuoteQueryService quoteQueryService /*,PlanningExternalProfileService planningExternalProfileService*/) {
        this.quoteCommandService = quoteCommandService;
        this.quoteQueryService = quoteQueryService;
        //this.planningExternalProfileService = planningExternalProfileService;
    }

    @GetMapping
    @Operation(summary = "Get quotes of an organizer", description = "Get quotes created by an organizer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quotes retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Quotes not found")
    })
    public ResponseEntity<List<QuoteResource>> getQuotesForOrganizerWithOrganizerId(@PathVariable Long organizerId){

        var getAllQuotesByOrganizerId = new GetAllQuotesByOrganizerIdQuery(organizerId);
        var quotes = quoteQueryService.handle(getAllQuotesByOrganizerId);
        var quoteResources = quotes.stream().map(QuoteResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(quoteResources);

        /*if(planningExternalProfileService.verifyIfExistProfileById(organizerId)){
            var getAllQuotesByOrganizerId = new GetAllQuotesByOrganizerIdQuery(organizerId);
            var quotes = quoteQueryService.handle(getAllQuotesByOrganizerId);
            var quoteResources = quotes.stream().map(QuoteResourceFromEntityAssembler::toResourceFromEntity).toList();
            return ResponseEntity.ok(quoteResources);
        }else {
            return ResponseEntity.notFound().build();
        }*/

    }


}
