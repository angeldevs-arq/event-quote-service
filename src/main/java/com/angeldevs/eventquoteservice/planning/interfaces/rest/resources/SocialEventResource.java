package com.angeldevs.eventquoteservice.planning.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.Date;

/**
 * Resource representing a social event response.
 */

public record SocialEventResource(Long id,
                                  String title,
                                  Date date,
                                  String customerName,
                                  String place,
                                  String status
                                  ) {
}
