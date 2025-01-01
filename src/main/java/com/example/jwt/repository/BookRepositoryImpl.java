package com.example.jwt.repository;

import com.example.jwt.dto.request.SearchTitleRequest;
import com.example.jwt.dto.BookDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.Projections;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.jwt.entity.QBook.book;


public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public BookRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BookDTO> searchBooks(String searchTitle) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        BookDTO.class,
                        book.title,
                        book.author,
                        book.publisher,
                        book.plot,
                        book.category,
                        book.bookNum))
                .from(book)
                .where(titleContains(searchTitle))
                .fetch();
    }

    private BooleanExpression titleContains(String searchTitle) {
        return (searchTitle == null || searchTitle.isEmpty()) ? null : book.title.containsIgnoreCase(searchTitle);
    }
}
