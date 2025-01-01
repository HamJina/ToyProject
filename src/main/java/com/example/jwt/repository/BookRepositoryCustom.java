package com.example.jwt.repository;

import com.example.jwt.dto.request.SearchTitleRequest;
import com.example.jwt.dto.BookDTO;

import java.util.List;

//사용자 정의 인터페이스
public interface BookRepositoryCustom {

    //구현할 검색 조건 함수
    List<BookDTO> searchBooks(String searchTitle);
}
