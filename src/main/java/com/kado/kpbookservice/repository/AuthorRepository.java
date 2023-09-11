package com.kado.kpbookservice.repository;

import com.kado.kpbookservice.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findAllByNameLikeIgnoreCase(String name);
}
