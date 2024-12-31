package com.example.jwt.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SaveBookRequest {
    private String title;
    private String author;
    private String publisher;
    private String plot;
    private String category;
    private int bookNum;
}
