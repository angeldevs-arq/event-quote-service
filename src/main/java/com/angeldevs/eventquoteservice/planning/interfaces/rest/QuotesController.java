package com.angeldevs.eventquoteservice.planning.interfaces.rest;

import com.angeldevs.eventquoteservice.planning.domain.model.commands.ConfirmQuoteCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.DeleteQuoteCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.commands.RejectQuoteCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.ExistsByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetQuoteByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;
import com.angeldevs.eventquoteservice.planning.domain.services.QuoteCommandService;
import com.angeldevs.eventquoteservice.planning.domain.services.QuoteQueryService;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.CreateQuoteResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.QuoteResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.UpdateQuoteResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.CreateQuoteCommandFromResourceAssembler;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.QuoteResourceFromEntityAssembler;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.UpdateQuoteCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/quotes",produces = APPLICATION_JSON_VALUE)
@Tag(name="Quotes", description = "Available Quotes Endpoints")
public class QuotesController {
    private final QuoteCommandService quoteCommandService;
    private final QuoteQueryService quoteQueryService;

    public QuotesController(QuoteCommandService quoteCommandService, QuoteQueryService quoteQueryService) {
        this.quoteCommandService = quoteCommandService;
        this.quoteQueryService = quoteQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new quote", description = "Create a new quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Quote created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    public ResponseEntity<QuoteResource> createQuote(@RequestBody CreateQuoteResource resource){
        var createdQuoteCommand = CreateQuoteCommandFromResourceAssembler.toCommandFromResource(resource);
        var quoteId = quoteCommandService.handle(createdQuoteCommand);
        if(quoteId == null) return ResponseEntity.badRequest().build();
        var getQuoteByIdQuery = new GetQuoteByQuoteIdQuery(new QuoteId(quoteId));
        var quote = quoteQueryService.handle(getQuoteByIdQuery);

        if(quote.isEmpty()) return ResponseEntity.notFound().build();

        var quoteEntity = quote.get();
        var quoteResource = QuoteResourceFromEntityAssembler.toResourceFromEntity(quoteEntity);
        return new ResponseEntity<>(quoteResource, HttpStatus.CREATED);
    }

    @GetMapping("/{quoteId}")
    @Operation(summary = "Get a quote by its id", description = "Get a quote by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quote found"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    public ResponseEntity<QuoteResource> getQuoteById(@PathVariable("quoteId") String quoteId){
        var _quoteId = new QuoteId(quoteId);
        var existsByQuoteIdQuery = new ExistsByQuoteIdQuery(_quoteId);
        if(!quoteQueryService.handle(existsByQuoteIdQuery)) return ResponseEntity.notFound().build();
        var getQuoteByQuoteIdQuery = new GetQuoteByQuoteIdQuery(_quoteId);
        var quote = quoteQueryService.handle(getQuoteByQuoteIdQuery);
        var quoteResource = QuoteResourceFromEntityAssembler.toResourceFromEntity(quote.get());
        return ResponseEntity.ok(quoteResource);
    }


    @PutMapping("/{quoteId}")
    @Operation(summary = "Update a quote", description = "Update a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quote updated"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    public ResponseEntity<QuoteResource> updateQuote(@PathVariable String quoteId, @RequestBody UpdateQuoteResource resource){
        var updateQuoteCommand = UpdateQuoteCommandFromResourceAssembler.toCommandFromResource(quoteId,resource);
        var updatedQuote = quoteCommandService.handle(updateQuoteCommand);
        if(updatedQuote.isEmpty()) return ResponseEntity.notFound().build();
        var updatedQuoteEntity = updatedQuote.get();
        var updateQuoteResource = QuoteResourceFromEntityAssembler.toResourceFromEntity(updatedQuoteEntity);
        return ResponseEntity.ok(updateQuoteResource);
    }

    @DeleteMapping("/{quoteId}")
    @Operation(summary = "Delete a quote", description = "Delete a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Quote deleted"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    public ResponseEntity<?> deleteQuote(@PathVariable String quoteId){
        var deleteQuoteCommand = new DeleteQuoteCommand(new QuoteId(quoteId));
        quoteCommandService.handle(deleteQuoteCommand);
        return ResponseEntity.ok(Map.of("message", "Quote deleted successfully"));
    }

    @PostMapping("/{quoteId}/confirmations")
    @Operation(summary = "Confirm Quote", description = "Confirm a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quote confirmed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<?> acceptQuote(@PathVariable String quoteId){
        var confirmQuoteCommand = new ConfirmQuoteCommand(new QuoteId(quoteId));
        var confirmedQuote = quoteCommandService.handle(confirmQuoteCommand);
        if(confirmedQuote == null) return ResponseEntity.badRequest().build();
        String message = "Quote confirmed successfully";
        return ResponseEntity.ok(message);
    }


    @PostMapping("/{quoteId}/rejections")
    @Operation(summary = "Reject a quote", description = "Reject a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quote rejected successfully"),
            @ApiResponse(responseCode = "400",description = "Bad Request")
    })
    public ResponseEntity<?> rejectQuote(@PathVariable String quoteId){
        var rejectQuoteCommand = new RejectQuoteCommand(new QuoteId(quoteId));
        var rejectedQuote = quoteCommandService.handle(rejectQuoteCommand);

        if(rejectedQuote == null){ return ResponseEntity.badRequest().build();}
        String message = "Quote rejected successfully";
        return ResponseEntity.ok(message);
    }
}
