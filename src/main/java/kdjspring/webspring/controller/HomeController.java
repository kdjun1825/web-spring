package kdjspring.webspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  //index.html 시작페이지는 스프링컨테이너를 먼저 찾기 때문에 home페이지로 가게 됨.
  @GetMapping("/")
  public String home() {
    return "home";
  }
}
