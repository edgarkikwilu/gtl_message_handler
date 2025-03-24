package com.example.file_processor.services;

import com.example.file_processor.configurations.RabbitMqConfiguration;
import com.example.file_processor.dtos.ContentRowData;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@RequiredArgsConstructor
public class MessageHandlerService {
    private JavaMailSender javaMailSender;
    private final Gson gson = new Gson();

    MessageHandlerService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = RabbitMqConfiguration.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);

        ContentRowData contentRowData = gson.fromJson(message,ContentRowData.class);

        //send email
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(contentRowData.getEmail());
        simpleMailMessage.setSubject(contentRowData.getName());
        simpleMailMessage.setText(contentRowData.getMessage());

        javaMailSender.send(simpleMailMessage);
    }
}
