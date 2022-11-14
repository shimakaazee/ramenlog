package com.jec.ramenlog.controller;

import com.jec.ramenlog.common.R;
import com.jec.ramenlog.entity.Employee;
import com.jec.ramenlog.entity.User;
import com.jec.ramenlog.mail.EmailSenderService;
import com.jec.ramenlog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by @author 卞凌志
 * on 2022/10/04 11:08
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @PostMapping
    public R<String> save(@RequestBody User user) {

        userService.save(user);

        return R.success("success");
    }
    @GetMapping("/auth")
    public R<String> authMail(@RequestParam String code) {
        String authCode = (String) redisTemplate.boundValueOps("code").get();
        System.out.println(authCode + "123");
        if (code.equals(authCode)) {
            String id = (String) redisTemplate.boundValueOps("id").get();
            return R.success("Mail Auth Success, " + id);
        } else {
            return R.error("The link has expired, auth failed");
        }
    }

    @PostMapping("/mail")
    public R<String> testSend(@RequestParam Map<String, Object> params) throws MessagingException {

        String mail = (String) params.get("mail");
        String id = (String) params.get("id");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        UUID uuid = UUID.randomUUID();
        redisTemplate.boundValueOps("id").set(id, 5, TimeUnit.MINUTES);
        redisTemplate.boundValueOps("code").set(uuid + "", 5, TimeUnit.MINUTES);

        String link = "http://localhost:8080/user/auth?code=" + uuid;
        link = "<a href='" + link + "'>ここだよ</a>";

        helper.setSubject("Welcome to ramenlog");
        helper.setText(buildContent(id, link), true);
        helper.setTo(mail);
        helper.setFrom("Ramenlog Official" + '<' + "shimakaazee@gmail.com" + '>');
        javaMailSender.send(message);

        return R.success("success");

    }

    public String buildContent(String name, String link) {
        //加载邮件html模板
        Resource resource = new ClassPathResource("template/mailtemplate.ftl");
        InputStream inputStream = null;
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            inputStream = resource.getInputStream();
            fileReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            log.info("发送邮件读取模板失败{}", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return MessageFormat.format(buffer.toString(), name, link);
    }


}
