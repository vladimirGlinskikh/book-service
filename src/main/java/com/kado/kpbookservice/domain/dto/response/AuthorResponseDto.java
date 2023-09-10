package com.kado.kpbookservice.domain.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthorResponseDto {
    UUID id;
    String name;
    String description;
    String imageUri;
}
