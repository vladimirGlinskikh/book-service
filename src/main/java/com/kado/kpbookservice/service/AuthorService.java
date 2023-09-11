package com.kado.kpbookservice.service;

import com.kado.kpbookservice.domain.dto.request.AuthorRequestDto;
import com.kado.kpbookservice.domain.dto.response.AuthorResponseDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    AuthorResponseDto save(AuthorRequestDto authorRequestDto);

    AuthorResponseDto update(AuthorRequestDto authorRequestDto, UUID id);

    AuthorResponseDto partialUpdate(AuthorRequestDto authorRequestDto, UUID id);

    AuthorResponseDto findById(UUID id);

    List<AuthorResponseDto> findAll();

    List<AuthorResponseDto> findAllByName(String name);

    void delete(UUID id);
}
