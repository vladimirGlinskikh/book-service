package com.kado.kpbookservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CategoryDto {
    @NotNull(message = "Id is required")
    Long id;

    String name;

    String description;

    String imageUri;
}
