package com.xyls.wwyz.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by Lee on 2017/6/12.
 */
@Component
public class EmailSendUtil {

    @Value("${spring.mail.usernam}")
    private String emailServer;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件
     * @param to			收件人地址
     * @param subject		邮件主题
     * @param content		邮件内容
     * @param html			是否格式内容为HTML
     * @author lance
     */

    public boolean sender(String[] to, String subject, String content, boolean html){

        if(to == null || to.length == 0) {
            return false;
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailServer);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);


        javaMailSender.send(simpleMailMessage);
        return true;
    }
}
