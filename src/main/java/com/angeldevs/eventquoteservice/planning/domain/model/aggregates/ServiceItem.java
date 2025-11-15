package com.angeldevs.eventquoteservice.planning.domain.model.aggregates;


import com.angeldevs.eventquoteservice.planning.domain.model.commands.CreateServiceItemCommand;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.QuoteId;
import com.angeldevs.eventquoteservice.planning.domain.model.valueobjects.ServiceItemId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ServiceItem extends AbstractAggregateRoot<ServiceItem> {

    @Id
    ServiceItemId serviceItemId;

    @Getter
    String description;
    @Getter
    int quantity;
    @Getter
    double unitPrice;

    double totalPrice;


    @Embedded
    QuoteId quoteId;

    @Column(nullable=false, updatable=false,columnDefinition = "datetime")
    @CreatedDate
    Date createdAt;

    @Column(nullable=false, columnDefinition = "datetime")
    @LastModifiedDate
    Date updatedAt;


    protected ServiceItem(){
        this.serviceItemId = new ServiceItemId();
    }

    public ServiceItem(CreateServiceItemCommand command){
        this();
        this.description = command.description();
        this.quantity = command.quantity();
        this.unitPrice = command.unitPrice();
        this.totalPrice = command.totalPrice();
        this.quoteId = command.quoteId();
    }

    public ServiceItem updateInformation(String description, int quantity, double unitPrice, double totalPrice) {
        if(description != null && !description.isBlank()){
            this.description = description;
        }
        if(quantity > 0){
            this.quantity = quantity;
        }
        if(unitPrice > 0){
            this.unitPrice = unitPrice;
        }
        if(totalPrice > 0){
            this.totalPrice = totalPrice;
        }
        return this;
    }

    public String getServiceItemId(){
        return this.serviceItemId.serviceItemId();
    }

    public String getQuoteId(){
        return this.quoteId.quoteId();
    }

}
