package com.example.jwt.service;

import com.example.jwt.dto.request.LoanBookRequest;
import com.example.jwt.dto.response.LoanResponse;
import com.example.jwt.entity.Book;
import com.example.jwt.entity.Loan;
import com.example.jwt.entity.User;
import com.example.jwt.repository.BookRepository;
import com.example.jwt.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<LoanResponse> loanList(User user) {
        List<Loan> loanByUser = loanRepository.findByUserId(user.getId());
        if (loanByUser.isEmpty()) {
            throw new IllegalArgumentException("대출도서가 존재하지 않습니다.");
        }

        // Loan 객체를 LoanResponse 객체로 변환하여 반환
        return loanByUser.stream()
                .map(loan -> new LoanResponse(
                        loan.getId(),
                        loan.getBook().getTitle(),
                        loan.getBook().getAuthor(),
                        loan.getLoanDate(),
                        loan.getReturnDate()
                ))
                .collect(Collectors.toList());
    }

    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("대출도서가 존재하지 않습니다."));
        loanRepository.delete(loan);
        loan.getBook().plusBookNum();
    }
}
