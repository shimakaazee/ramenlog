package com.jec.ramenlog.mail;

import com.jec.ramenlog.common.R;
import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by @author 卞凌志
 * on 2022/10/13 12:19
 */

@SpringBootTest
public class testMail {

    @Autowired
    private EmailSenderService senderService;
    @Autowired
    private ShopService shopService;
    @Test
    public void testMail(){

        senderService.sendEmail("jellyman2012@gmail.com", "Welcome", "Welcome to ramenlog!");


    }


}
