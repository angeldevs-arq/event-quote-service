package com.angeldevs.eventquoteservice.planning.domain.services;

import com.angeldevs.eventquoteservice.planning.domain.model.aggregates.SocialEvent;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetAllSocialEventQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventByStatusQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventByTitleQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventsByOrganizerQuery;
import com.angeldevs.eventquoteservice.planning.domain.model.queries.GetSocialEventByIdQuery;
import java.util.Optional;

import java.util.List;

/**
 * Service interface for handling social event queries.
 */

public interface SocialEventQueryService {

    /**
     * Retrieves all social events.
     *
     * @param query the query to get all events
     * @return list of all social events
     */
    List<SocialEvent> handle(GetAllSocialEventQuery query);

    /**
     * Retrieves social events by status.
     *
     * @param query the query containing the status filter
     * @return list of social events with the specified status
     */
    List<SocialEvent> handle(GetSocialEventByStatusQuery query);

    /**
     * Retrieves social events by title.
     *
     * @param query the query containing the title filter
     * @return list of social events with the specified title
     */
    List<SocialEvent> handle(GetSocialEventByTitleQuery query);

    /**
     * Retrieves social events by organizer (customer name).
     *
     * @param query the query containing the customer name filter
     * @return list of social events organized by the specified customer
     */
    List<SocialEvent> handle(GetSocialEventsByOrganizerQuery query);

    /**
     * Retrieves a social event by its id.
     *
     * @param query the query containing the social event id
     * @return optional social event
     */
    Optional<SocialEvent> handle(GetSocialEventByIdQuery query);
}
