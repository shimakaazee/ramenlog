package com.jec.ramenlog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jec.ramenlog.common.R;
import com.jec.ramenlog.common.StringConstant;
import com.jec.ramenlog.entity.Category;
import com.jec.ramenlog.entity.Employee;
import com.jec.ramenlog.entity.Shop;
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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;
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
@CrossOrigin
public class UserController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;


    @PutMapping
    public R<String> update(@RequestBody User user) {
        String pass = user.getPassword();
        String mail = user.getMail();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("id", user.getId());

        User u = userService.getOne(queryWrapper);

        if (!(null == pass)) {
            u.setPassword(DigestUtils.md5DigestAsHex(pass.getBytes()));
        }

        if (!(null == mail)) {
            u.setMail(DigestUtils.md5DigestAsHex(mail.getBytes()));
        }

        userService.updateById(u);

        return R.success("update success");
    }

    @PostMapping
    public R<String> save(@RequestBody User user) {
        System.out.println(user);

        if (user.getAccessCode().equals(StringConstant.ACCESSCODE)) {
            user.setAccessCode("");
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setMail(DigestUtils.md5DigestAsHex(user.getMail().getBytes()));
            userService.save(user);

            return R.success("success");
        }


        return R.error("error");


    }

    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {

        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount());
        User usr = userService.getOne(queryWrapper);

        if (usr == null) {
            return R.error("ログイン失敗");
        }

        if (!usr.getPassword().equals(password)) {
            return R.error("ログイン失敗");
        }


        return R.success(usr);
    }

    @PostMapping("/check")
    public R<String> checkEmailAndAccount(@RequestBody User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (user.getMail() != null) {
            String mailMd5 = DigestUtils.md5DigestAsHex(user.getMail().getBytes());
            queryWrapper.eq(User::getMail, mailMd5);
            User u = userService.getOne(queryWrapper);
            if (u == null) {
                return R.success("mail not taken");
            } else {
                return R.error("mail taken");
            }
        }

        if (user.getAccount() != null) {
            queryWrapper.eq(User::getAccount, user.getAccount());
            User u = userService.getOne(queryWrapper);
            System.out.println(u);
            if (u == null) {
                return R.success("account not taken");
            } else {
                return R.error("account taken");
            }
        }
        return null;

    }

    @GetMapping("/{id}")
    public R<User> getById(@PathVariable Long id) {

        User user = userService.getById(id);
        return R.success(user);
    }

    @GetMapping("/list")
    public R<List<User>> list() {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();


        List<User> list = userService.list();

        return R.success(list);
    }

    @GetMapping("/auth")
    public R<String> authMail(@RequestParam String code, HttpServletResponse response) throws IOException {
        String authCode = (String) redisTemplate.boundValueOps("code").get();
        String id = (String) redisTemplate.boundValueOps("id").get();
        if (code.equals(authCode)) {
            response.sendRedirect("https://www.eatingmap.fun/view/authority/mail_confirm/index.php?status=1&id=" + id);
            return R.success("Success");
        } else {
            response.sendRedirect("https://www.eatingmap.fun/view/authority/mail_confirm/index.php?status=0&id=" + id);
            return R.error("The link has expired, auth failed");
        }
    }

    @PostMapping("/mail")
    public R<String> testSend(@RequestBody User user) throws MessagingException {

        String mail = user.getMail();
        String id = user.getAccount();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        UUID uuid = UUID.randomUUID();
        redisTemplate.boundValueOps("id").set(id, 5, TimeUnit.MINUTES);
        redisTemplate.boundValueOps("code").set(uuid + "", 5, TimeUnit.MINUTES);

        String link = StringConstant.URL + "/user/auth?code=" + uuid;
        link = "<a href='" + link + "'>認証はこちらです</a>";

        helper.setSubject("Welcome to Eatingmap");
        helper.setText(buildContent(id, link), true);
        helper.setTo(mail);
        helper.setFrom("Eatingmap Official" + '<' + "shimakaazee@gmail.com" + '>');
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
