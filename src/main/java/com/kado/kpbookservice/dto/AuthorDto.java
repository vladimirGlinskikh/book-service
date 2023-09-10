package com.kado.kpbookservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AuthorDto {
    @NotNull(message = "Id is required")
    private UUID id;

    private String name;

    private String description;

    private String imageUri;
}
