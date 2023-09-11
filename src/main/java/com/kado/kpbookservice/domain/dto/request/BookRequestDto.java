package com.kado.kpbookservice.domain.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {

    @NotEmpty(message = "Image URI is required")
    String imageUri;

    @NotEmpty(message = "Title is required")
    String title;

    @NotEmpty(message = "Description is required")
    String description;

    @NotNull(message = "Count page is required")
    Long countPage;

    @NotNull(message = "Authors is required")
    @Size(min = 1, message = "Authors is required")
    @Valid
    List<AuthorBookRequestDto> authors = new ArrayList<>();

    @NotNull(message = "Category is required")
    Long category;

    UUID userId;
}
