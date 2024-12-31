package com.example.jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Lazy;

@Table(name = "Books")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    private String plot;

    private String category;

    @Column(name = "book_num")
    private int bookNum;

    public Book(String title, String author, String publisher, String plot, String category, int bookNum) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.plot = plot;
        this.category = category;
        this.bookNum = bookNum;
    }
}
