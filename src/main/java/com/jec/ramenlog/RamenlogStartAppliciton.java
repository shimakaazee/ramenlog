package com.jec.ramenlog;

import com.jec.ramenlog.mail.EmailSenderService;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class RamenlogStartAppliciton {
    @Autowired
    static private EmailSenderService senderService;
    public static void main(String[] args) {
        SpringApplication.run(RamenlogStartAppliciton.class, args);

    }

}
