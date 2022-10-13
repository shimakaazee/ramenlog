package com.jec.ramenlog.controller;

import com.jec.ramenlog.common.R;
import com.jec.ramenlog.entity.User;
import com.jec.ramenlog.mail.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by @author 卞凌志
 * on 2022/10/04 11:08
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/mail")
    public R<String> testSend(@RequestBody User user) {

        String id = user.getId();
        String mail = user.getMail();

        emailSenderService.sendEmail(mail, "Welcome", "Welcome to ramenlog, " + id + "!");
        return R.success("success");

    }
}
