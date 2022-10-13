package com.jec.ramenlog.mail;

import com.jec.ramenlog.common.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by @author 卞凌志
 * on 2022/10/13 12:19
 */

public class testMail {

    @Autowired
    private EmailSenderService senderService;
    @Test
    public void testMail(){

        senderService.sendEmail("jellyman2012@gmail.com", "Welcome", "Welcome to ramenlog!");


    }
}
