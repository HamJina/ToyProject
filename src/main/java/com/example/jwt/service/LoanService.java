package com.example.jwt.service;

import com.example.jwt.dto.request.LoanBookRequest;
import com.example.jwt.entity.Book;
import com.example.jwt.entity.Loan;
import com.example.jwt.entity.User;
import com.example.jwt.repository.BookRepository;
import com.example.jwt.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public void loanBook(LoanBookRequest request, User currentUser) {
        // request의 bookId로 도서를 조회
        Book findBook = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));

        // 대출 가능한 재고 확인
        if (findBook.getBookNum() == 0) {
            throw new IllegalArgumentException("대출 불가: 해당 도서의 재고가 없습니다.");
        }

        // 대출 가능한 경우 대출 처리
        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plusDays(14);

        // 대출 정보 저장
        Loan loan = new Loan();
        loan.setBook(findBook);
        loan.setLoanDate(loanDate);
        loan.setReturnDate(returnDate);
        loan.setUser(currentUser);
        loanRepository.save(loan);

        findBook.minusBookNum();
    }
}
