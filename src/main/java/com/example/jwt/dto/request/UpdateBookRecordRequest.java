package com.example.jwt.dto.request;

import lombok.Data;

@Data
public class UpdateBookRecordRequest {

    private Long bookId;
    private String cocntent;
}
