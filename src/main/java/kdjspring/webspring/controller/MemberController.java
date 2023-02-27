package kdjspring.webspring.controller;

import kdjspring.webspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//Controller에서 의존성주입을 해서 사용 , Service, Repository 각각 annotation을 붙여줌 con -> sevice -> repository
//or 직접 자바 코드 작성으로 의존성 주입 ->SpringConfig
@Controller
public class MemberController {
  private final MemberService memService;

  //컨트롤러가 생성될 때 서비스객체를 넣어주는 것이 D.I (의존성 주입)
  @Autowired
  public MemberController(MemberService memService) {
    this.memService = memService;
  }
}
