package com.angeldevs.eventquoteservice.planning.interfaces.rest;

import com.angeldevs.eventquoteservice.planning.domain.model.commands.DeleteSocialEventCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllSocialEventQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventByStatusQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventByTitleQuery;
import com.angeldevs.eventquoteservice.planning.domain.services.SocialEventCommandService;
import com.angeldevs.eventquoteservice.planning.domain.services.SocialEventQueryService;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.CreateSocialEventResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.SocialEventResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.UpdateSocialEventResource;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.CreateSocialEventCommandFromResourceAssembler;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.SocialEventResourceFromEntityAssembler;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.transform.UpdateSocialEventCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing social events.
 */
@RestController
@RequestMapping(value = "/api/v1/social-events", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Social Events", description = "Operations related to social events management")
public class SocialEventsController {

    private final SocialEventCommandService socialEventCommandService;
    private final SocialEventQueryService socialEventQueryService;

    public SocialEventsController(SocialEventCommandService socialEventCommandService,
                                  SocialEventQueryService socialEventQueryService) {
        this.socialEventCommandService = socialEventCommandService;
        this.socialEventQueryService = socialEventQueryService;
    }

    /**
     * Creates a new social event.
     */
    @Operation(summary = "Create a new social event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Social event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<SocialEventResource> createSocialEvent(@RequestBody CreateSocialEventResource resource) {
        try {
            var createCommand = CreateSocialEventCommandFromResourceAssembler.toCommandFromResource(resource);
            var socialEvent = socialEventCommandService.handle(createCommand);

            if (socialEvent.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            var socialEventResource = SocialEventResourceFromEntityAssembler.toResourceFromEntity(socialEvent.get());
            return new ResponseEntity<>(socialEventResource, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves all social events.
     */
    @Operation(summary = "Get all social events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Social events retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<SocialEventResource>> getAllSocialEvents() {
        var getAllQuery = new GetAllSocialEventQuery();
        var socialEvents = socialEventQueryService.handle(getAllQuery);

        var socialEventResources = socialEvents.stream()
                .map(SocialEventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(socialEventResources);
    }

    /**
     * Updates an existing social event.
     */
    @Operation(summary = "Update an existing social event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Social event updated successfully"),
            @ApiResponse(responseCode = "404", description = "Social event not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{socialEventId}")
    public ResponseEntity<SocialEventResource> updateSocialEvent(@PathVariable Long socialEventId,
                                                                 @RequestBody UpdateSocialEventResource resource) {
        try {
            var updateCommand = UpdateSocialEventCommandFromResourceAssembler.toCommandFromResource(resource, socialEventId);
            var socialEvent = socialEventCommandService.handle(updateCommand);

            if (socialEvent.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var socialEventResource = SocialEventResourceFromEntityAssembler.toResourceFromEntity(socialEvent.get());
            return ResponseEntity.ok(socialEventResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deletes a social event.
     */
    @Operation(summary = "Delete a social event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Social event deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Social event not found")
    })
    @DeleteMapping("/{socialEventId}")
    public ResponseEntity<Void> deleteSocialEvent(@PathVariable Long socialEventId) {
        try {
            var deleteCommand = new DeleteSocialEventCommand(socialEventId);
            socialEventCommandService.handle(deleteCommand);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves social events by status.
     */
    @Operation(summary = "Get social events by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Social events retrieved successfully")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<SocialEventResource>> getSocialEventsByStatus(@PathVariable String status) {
        var getByStatusQuery = new GetSocialEventByStatusQuery(status);
        var socialEvents = socialEventQueryService.handle(getByStatusQuery);

        var socialEventResources = socialEvents.stream()
                .map(SocialEventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(socialEventResources);
    }

    /**
     * Retrieves social events by title.
     */
    @Operation(summary = "Get social events by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Social events retrieved successfully")
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<List<SocialEventResource>> getSocialEventsByTitle(@PathVariable String title) {
        var getByTitleQuery = new GetSocialEventByTitleQuery(title);
        var socialEvents = socialEventQueryService.handle(getByTitleQuery);

        var socialEventResources = socialEvents.stream()
                .map(SocialEventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(socialEventResources);
    }
}