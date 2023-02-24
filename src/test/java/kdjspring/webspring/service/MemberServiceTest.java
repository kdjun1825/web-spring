package kdjspring.webspring.service;

import kdjspring.webspring.domain.Member;
import kdjspring.webspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

  MemberService memService;

  //memberService의 MemoryMemberRepository객체가 다른 인스턴스 임.같은 인스턴스로 테스트 하는게 정확
  MemoryMemberRepository memRepo;

  //각 메서드 실행하기 전에 객체를 생성하고 service에 보내줌  이렇게 하면 같은 객체, 같은 인스턴스로 사용함
  @BeforeEach
  public void beforeEach() {
    memRepo = new MemoryMemberRepository();
    memService = new MemberService(memRepo);
  }


  //testmethod하나 돌 때마다 클리어
  //join() setName 에 hello가 아니고 spring 이 되면 테스트롤 통과 못하는데 testCode는 각 메서드별로 검증해야되서 다 통과 되게 해야됨
  @AfterEach
  public void afterEach(){
    memRepo.clearStore();
  }


  //testcode 한글로 해도 됨
  @Test
  void join() {
    //given
    Member member = new Member();
    member.setName("hello");

    //when
    long saveId = memService.join(member);//join()을 검증

    //then
    Member findMember = memService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());

  }
  //join()의 예외도 확인해보는게 좋다!
  @Test
  public void exceptJoin(){
    //given
    Member member1 = new Member();
    member1.setName("spring");
    Member member2 = new Member();
    member2.setName("spring");

    //when
    memService.join(member1);
    //memService.join(member2) 가 실행될 때 illegalStateException 예외가 터져야 함!  npe같은걸로 하면 optional때매 통과 안됨
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memService.join(member2));
    //발생하는 예외처리에서 나오는 메세지를 isequalto로 비교
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

   /* try {
      memService.join(member2);

    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }*/

    //then

  }
  
  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}