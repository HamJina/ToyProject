package com.example.jwt.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveBookRecordRequest {

    private Long bookId;
    private String content;

}
