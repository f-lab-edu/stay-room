package com.example.api.service;

import com.example.base.enums.ErrorType;
import com.example.base.exception.CommonException;
import jdk.jshell.spi.ExecutionControl.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender javaMailSender;
  private static final String VERIFY_TITLE = "[stay-room] 이메일 인증 코드";
  private static final String VERIFY_TEXT_PREFIX = "아래에 나열된 이메일 인증 코드를 복사하여 입력하세요\n\n인증 코드: ";

  public void sendVerifyCodeEmail(String email, String code) {
    String text = VERIFY_TEXT_PREFIX + code;
    SimpleMailMessage emailForm = createEmailForm(email, VERIFY_TITLE, text);
    try {
      javaMailSender.send(emailForm);
    } catch (RuntimeException e) {
      e.printStackTrace();
      throw new CommonException(ErrorType.BAD_REQUEST);
    }
  }

  private SimpleMailMessage createEmailForm(String email, String title, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject(title);
    message.setText(text);
    return message;
  }



}
