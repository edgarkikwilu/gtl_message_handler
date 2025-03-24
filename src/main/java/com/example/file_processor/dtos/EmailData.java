package com.example.file_processor.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailData {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
