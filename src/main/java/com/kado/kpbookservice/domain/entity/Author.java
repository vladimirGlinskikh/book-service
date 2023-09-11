package com.kado.kpbookservice.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@Data
public class Author {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String name;

    private String description;

    private String imageUri;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "authors")
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();

}
