package com.kado.kpbookservice.domain.dto.response;

import com.kado.kpbookservice.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BookResponseDto {
    UUID id;
    String imageUri;
    String title;
    String description;
    List<AuthorResponseDto> authors;
    CategoryResponseDto category;
    Long countPage;
    UUID userId;
    Long progress;
}
