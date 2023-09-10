package com.kado.kpbookservice.repository;

import com.kado.kpbookservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
