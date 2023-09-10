package com.kado.kpbookservice.domain.dto.request;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AuthorRequestDto {
    String name;
    String description;
    String imageUri;
}
