package com.kado.kpbookservice.repository;

import com.kado.kpbookservice.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Book> findAllByTitleLikeIgnoreCase(String name, Pageable pageable);
}
