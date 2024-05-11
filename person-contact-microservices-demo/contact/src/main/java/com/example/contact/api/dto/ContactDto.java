package com.example.contact.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Contact DTO used when returning to the client.")
public class ContactDto extends ContactRef {

}
