package com.angeldevs.eventquoteservice.planning.domain.model.aggregates;


import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.*;
import com.angeldevs.eventquoteservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*
*SocialEvent Aggregate Root
*
*/
@Entity
@Table(name = "social_events")
public class SocialEvent extends AuditableAbstractAggregateRoot<SocialEvent>
{

    @Embedded
    private SocialEventTitle title;

    @Embedded
    private SocialEventDate date;

    @Embedded
    private CustomerName customerName;

    @Embedded
    private Place place;

    @Embedded
    private SocialEventStatus status;






    /**
     * Constructor with full value objects.
     *
     * @param title        the title of the event
     * @param place        the location of the event
     * @param date         the scheduled date
     * @param customerName the customer full name
     * @param status       the current status of the event
     */
    public SocialEvent(SocialEventTitle title, Place place, SocialEventDate date,
                       CustomerName customerName, SocialEventStatus status) {
        this.title = title;
        this.date = date;
        this.customerName = customerName;
        this.place = place;
        this.status = status;

    }

    protected SocialEvent() {}

    // Getters for each embedded value object

    public String getTitle() {
        return title.title();
    }

    public String getPlace() {
        return place.place();
    }

    public String getCustomerName() {
        return customerName.customerName();
    }

    public Date getDate() {
        return date.eventDate();
    }

    public String getEventStatus() {
        return status.valueStatus();
    }


    /**
     * Allows updating the event's status.
     *
     * @param newStatus the new status to set
     */
    public void updateStatus(String newStatus) {
        this.status = new SocialEventStatus(newStatus);


    }

    /**
     * Allows updating basic event details.
     *
     * @param newTitle new title
     * @param newPlace new place
     * @param newDate  new date
     */
    public void updateDetails(String newTitle, Date newDate, String newPlace, String newCustomerName ) {
        this.title = new SocialEventTitle(newTitle);
        this.date = new SocialEventDate(newDate);
        this.customerName = new CustomerName(newCustomerName); // Assuming customer name remains unchanged
        this.place = new Place(newPlace);

    }
}



