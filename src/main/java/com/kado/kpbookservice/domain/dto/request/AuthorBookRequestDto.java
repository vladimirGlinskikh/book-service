package com.kado.kpbookservice.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AuthorBookRequestDto {
    @NotNull(message = "Id is required")
    UUID id;
}
