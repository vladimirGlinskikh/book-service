package com.kado.kpbookservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    UUID id;

    @NotEmpty(message = "Image URI is required")
    String imageUri;

    @NotEmpty(message = "Title is required")
    String title;

    @NotEmpty(message = "Description is required")
    String description;

    @NotNull(message = "Authors is required")
    @Valid
    List<AuthorDto> authors = new ArrayList<>();

    @NotNull(message = "Category is required")
    @Valid
    CategoryDto category;

    @NotNull(message = "Count page is required")
    Long countPage;

    UUID userId;

    Long progress;
}
