package com.example.jwt.dto.request;

import lombok.Data;

@Data
public class UpdateBookRecordRequest {

    private Long recordBookId;
    private String content;
}
