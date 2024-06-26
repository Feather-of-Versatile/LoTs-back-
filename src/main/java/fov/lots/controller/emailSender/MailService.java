package fov.lots.Controller.EmailSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {
  
  private final JavaMailSender mailSender;

  public void sendEmail(String toEmail, String title, String text) {

    SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
    try {
      mailSender.send(emailForm);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }

  // 이메일 발송 데이터 폼.안승준
  private SimpleMailMessage createEmailForm(String toEmail, String title, String text) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject(title);
    message.setText(text);

    return message;

  }

}
