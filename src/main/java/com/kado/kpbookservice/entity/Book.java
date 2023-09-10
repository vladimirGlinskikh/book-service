package com.kado.kpbookservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String imageUri;

    private String title;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "books",cascade = CascadeType.ALL)
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    private Long countPage;

    private UUID userId;

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
}
