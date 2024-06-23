package fov.lots.Controller.Common;

import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {

  @RequestMapping(value = "/main")
  public String mainController() {
    return "mainPage";
  }

}