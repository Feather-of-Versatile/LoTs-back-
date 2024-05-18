package fov.lots.Global.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailPropertie {
  private String host;
  private int port;
  private String username;
  private String password;
  private boolean auth;
  private boolean starttlsEnable;
  private boolean starttlsRequired;
  private int connectionTimeout;
  private int timeout;
  private int writeTimeout;
}