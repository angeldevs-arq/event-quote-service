package com.angeldevs.eventquoteservice.planning.interfaces.rest;

import com.angeldevs.eventquoteservice.planning.domain.model.commands.DeleteServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.ExistsByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllServiceItemsByQuoteIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetServiceItemByIdQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.ServiceItemId;
import com.angeldevs.eventquoteservice.planning.domain.services.QuoteQueryService;
import com.angeldevs.eventquoteservice.planning.domain.services.ServiceItemCommandService;
import com.angeldevs.eventquoteservice.planning.domain.services.ServiceItemQueryService;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.CreateServiceItemResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.ServiceItemResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.UpdateServiceItemResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.CreateServiceItemCommandFromResourceAssembler;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.ServiceItemResourceFromEntityAssembler;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.UpdateServiceItemCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/quotes/{quoteId}/service-items", produces = APPLICATION_JSON_VALUE)
@Tag(name="Quotes")
public class QuoteServiceItemsController {

    private final ServiceItemCommandService serviceItemCommandService;
    private final ServiceItemQueryService serviceItemQueryService;
    private final QuoteQueryService  quoteQueryService;

    public QuoteServiceItemsController(ServiceItemCommandService serviceItemCommandService, ServiceItemQueryService serviceItemQueryService, QuoteQueryService quoteQueryService) {
        this.serviceItemCommandService = serviceItemCommandService;
        this.serviceItemQueryService = serviceItemQueryService;
        this.quoteQueryService = quoteQueryService;
    }

    @GetMapping
    @Operation(summary = "Get service items of a quote", description = "Get service items of a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service items retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    public ResponseEntity<List<ServiceItemResource>> getServiceItemsForQuoteWithQuoteId(@PathVariable String quoteId){
        var _quoteId = new QuoteId(quoteId);
        var existsByQuoteIdQuery = new ExistsByQuoteIdQuery(_quoteId);
        if(!quoteQueryService.handle(existsByQuoteIdQuery)) return ResponseEntity.notFound().build();
        var getAllServiceItemsByQuoteIdQuery = new GetAllServiceItemsByQuoteIdQuery(_quoteId);
        var serviceItems = serviceItemQueryService.handle(getAllServiceItemsByQuoteIdQuery);
        var serviceItemResources =  serviceItems.stream().map(ServiceItemResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(serviceItemResources);
    }


    @PostMapping
    @Operation(summary = "Create a new service item for a quote", description = "Create a new service item for a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Service item created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    public ResponseEntity<ServiceItemResource> createServiceItem(@PathVariable String quoteId, @RequestBody CreateServiceItemResource resource){
        var _quoteId = new QuoteId(quoteId);
        var existsByQuoteIdQuery = new ExistsByQuoteIdQuery(_quoteId);
        if(!quoteQueryService.handle(existsByQuoteIdQuery)) return ResponseEntity.notFound().build();
        var createServiceItemCommand = CreateServiceItemCommandFromResourceAssembler.toCommandFromResource(resource);
        var serviceItemId = serviceItemCommandService.handle(createServiceItemCommand);
        if(serviceItemId == null) return ResponseEntity.badRequest().build();
        var getServiceItemByIdQuery = new GetServiceItemByIdQuery(new ServiceItemId(serviceItemId));
        var serviceItem = serviceItemQueryService.handle(getServiceItemByIdQuery);
        if(serviceItem.isEmpty()) return ResponseEntity.badRequest().build();

        var serviceItemEntity = serviceItem.get();
        var serviceItemResource = ServiceItemResourceFromEntityAssembler.toResourceFromEntity(serviceItemEntity);
        return new ResponseEntity<>(serviceItemResource, HttpStatus.CREATED);
    }

    @PutMapping("/{serviceItemId}")
    @Operation(summary = "Update a service item of a quote", description = "Update a service item of a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "service item updated"),
            @ApiResponse(responseCode = "404", description = "service item not found")
    })
    public ResponseEntity<ServiceItemResource> updateServiceItem(@PathVariable String quoteId, @PathVariable String serviceItemId, @RequestBody UpdateServiceItemResource resource){
        var _quoteId = new QuoteId(quoteId);

        var existsByQuoteIdQuery = new ExistsByQuoteIdQuery(_quoteId);
        if(!quoteQueryService.handle(existsByQuoteIdQuery)) return ResponseEntity.notFound().build();

        var updateServiceItemCommand = UpdateServiceItemCommandFromResourceAssembler.toCommandFromResource(serviceItemId,resource);
        var updatedServiceItem = serviceItemCommandService.handle(updateServiceItemCommand);
        if(updatedServiceItem.isEmpty()) return ResponseEntity.badRequest().build();
        var updatedServiceItemEntity = updatedServiceItem.get();
        var updatedServiceItemResource = ServiceItemResourceFromEntityAssembler.toResourceFromEntity(updatedServiceItemEntity);
        return ResponseEntity.ok(updatedServiceItemResource);

    }

    @DeleteMapping("/{serviceItemId}")
    @Operation(summary = "Delete a service item of a quote", description = "Delete a service item of a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service item deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Service item not found")
    })
    public ResponseEntity<?> deleteServiceItem(@PathVariable String quoteId, @PathVariable String serviceItemId){
        var _quoteId = new QuoteId(quoteId);
        var existsByQuoteIdQuery = new ExistsByQuoteIdQuery(_quoteId);
        if(!quoteQueryService.handle(existsByQuoteIdQuery)) return ResponseEntity.badRequest().build();
        var deleteServiceItemCommand = new DeleteServiceItemCommand(new ServiceItemId(serviceItemId));
        serviceItemCommandService.handle(deleteServiceItemCommand);
        return ResponseEntity.ok(Map.of("message","Service item with given id successfully deleted"));
    }
}
