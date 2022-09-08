package com.movie.movie.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender; //可直接注⼊邮件发送的对象

    @Value("${spring.mail.username}") //动态获取配置⽂件中发送邮件的账户
    private String sendemail;

    public String sendEmail(String email){
        //随机⽣成⼀个四位数的验证码
        int code = new Random().nextInt(9999) % (9999 - 1000 + 1) + 1000;
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //发送邮件账户
        message.setFrom(sendemail);
        //谁要接收
        message.setTo(email);
        //邮件标题
        message.setSubject("接收验证码");
        //邮件内容
        message.setText("您的验证码是" + code);
        javaMailSender.send(message);
        return  String.valueOf(code);
    }

}
