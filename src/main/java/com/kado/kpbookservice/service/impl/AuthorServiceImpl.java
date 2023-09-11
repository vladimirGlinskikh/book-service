package com.kado.kpbookservice.service.impl;

import com.kado.kpbookservice.domain.dto.request.AuthorRequestDto;
import com.kado.kpbookservice.domain.dto.response.AuthorResponseDto;
import com.kado.kpbookservice.domain.entity.Author;
import com.kado.kpbookservice.exception.NotFoundException;
import com.kado.kpbookservice.mapper.AuthorMapper;
import com.kado.kpbookservice.repository.AuthorRepository;
import com.kado.kpbookservice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    @Override
    @Transactional
    public AuthorResponseDto save(AuthorRequestDto authorRequestDto) {
        Author entity = authorMapper.toEntity(authorRequestDto);
        return authorMapper.toDto(authorRepository.save(entity));
    }

    @Override
    @Transactional
    public AuthorResponseDto update(AuthorRequestDto authorRequestDto, UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id %s not found", id)));
        Author entity = authorMapper.toEntity(authorRequestDto);
        entity.setId(author.getId());
        return authorMapper.toDto(authorRepository.save(entity));
    }

    @Override
    @Transactional
    public AuthorResponseDto partialUpdate(AuthorRequestDto authorRequestDto, UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id %s not found", id)));
        authorMapper.partialUpdate(author, authorRequestDto);
        author.setId(id);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Override
    public AuthorResponseDto findById(UUID id) {
        return authorMapper.toDto(authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id %s not found", id))));
    }

    @Override
    public List<AuthorResponseDto> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<AuthorResponseDto> findAllByName(String name) {
        return authorRepository.findAllByNameLikeIgnoreCase(name)
                .stream()
                .map(authorMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        authorMapper.toDto(authorRepository.findById(id)
                .map(author -> {
                    authorRepository.delete(author);
                    return author;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Author with id %s not found", id))));
    }
}
