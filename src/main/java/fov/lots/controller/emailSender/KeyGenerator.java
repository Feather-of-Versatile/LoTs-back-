package FoV.LoTs.controller.emailSender;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class KeyGenerator {

  public String keyGenerator() {

    // 인증키에 사용되는 문자.안승준
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random rd = new Random();
    StringBuilder key = new StringBuilder();

    for (int i = 0; i < 6; i++) {
      int index = rd.nextInt(characters.length());
      key.append(characters.charAt(index));
    }

    System.out.println("KeyGenerator Out로그=["+key+"]");

    // 인증키 반환.안승준
    return key.toString();

  }

}