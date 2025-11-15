package com.angeldevs.eventquoteservice.planning.interfaces.rest.transform;

import com.angeldevs.eventquoteservice.planning.domain.model.commands.CreateNewSocialEventCommand;
import com.angeldevs.eventquoteservice.planning.interfaces.rest.resources.CreateSocialEventResource;

/**
 * Assembler to convert CreateSocialEventResource to CreateNewSocialEventCommand.
 */
public class CreateSocialEventCommandFromResourceAssembler {
    /**
     * Converts a CreateSocialEventResource to a CreateNewSocialEventCommand.
     *
     * @param resource the resource to convert
     * @return the corresponding command
     */
    public static CreateNewSocialEventCommand toCommandFromResource(CreateSocialEventResource resource) {
        return new CreateNewSocialEventCommand(
                resource.title(),
                resource.date(),
                resource.customerName(),
                resource.place(),
                resource.status()
        );
    }
}
