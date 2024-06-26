package FoV.LoTs.Global.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.RequiredArgsConstructor;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Autowired
    private fov.lots.Global.Config.MailPropertie mailProperties;

    @Value("${spring.mail.host}")
    private String host;
  
    @Value("${spring.mail.port}")
    private int port;
  
    @Value("${spring.mail.username}")
    private String username;
  
    @Value("${spring.mail.password}")
    private String password;
  
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;
  
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttlsEnable;
  
    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private boolean starttlsRequired;
  
    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private int connectionTimeout;
  
    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private int timeout;
  
    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private int writeTimeout;
  
    @Bean
    public JavaMailSender javaMailSender() {
      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      mailSender.setHost(mailProperties.getHost());
      mailSender.setPort(mailProperties.getPort());
      mailSender.setUsername(mailProperties.getUsername());
      mailSender.setPassword(mailProperties.getPassword());
      mailSender.setDefaultEncoding("UTF-8");
      mailSender.setJavaMailProperties(getMailProperties());

      return mailSender;
    }

    private Properties getMailProperties() {
      Properties properties = new Properties();
      properties.put("mail.smtp.auth", mailProperties.isAuth());
      properties.put("mail.smtp.starttls.enable", mailProperties.isStarttlsEnable());
      properties.put("mail.smtp.starttls.required", mailProperties.isStarttlsRequired());
      properties.put("mail.smtp.connectiontimeout", mailProperties.getConnectionTimeout());
      properties.put("mail.smtp.timeout", mailProperties.getTimeout());
      properties.put("mail.smtp.writetimeout", mailProperties.getWriteTimeout());

      return properties;
    }
}
