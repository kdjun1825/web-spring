package kdjspring.webspring.controller;

import kdjspring.webspring.domain.Member;
import kdjspring.webspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMemberForm";
  }

  //등록을 눌렀을 때 post방식으로 전송 후 join실행
  @PostMapping("/members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memService.join(member);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}
