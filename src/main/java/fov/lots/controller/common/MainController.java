package fov.lots.Controller.common;

import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {

  @RequestMapping(value = "/main")
  public String mainController() {
    return "mainPage";
  }

}