package com.example.demo.model;

import lombok.Data;

@Data
public class Question {
    private String question;
    private String type;
    private String answer;
    private String select;
    private String author;
}
