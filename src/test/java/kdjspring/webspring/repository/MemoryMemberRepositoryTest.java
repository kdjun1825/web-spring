package kdjspring.webspring.repository;

import kdjspring.webspring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

  /*
  개발한 기능을 main에서 보통 실행해서 컨트롤러를 이용해 테스트 하는데 오래걸리고 반복하기 껄끄럽기 때문에
  자바가 제공하는 JUnit 프레임워크로 테스트를 진행할 수 있다.
   */
  MemoryMemberRepository repo = new MemoryMemberRepository();

  //Test코드는 실행 순서를 보장해주지 않기때문에 각각의 메서드는 각자 따로 검증되게 만들어야하고
  // 테스트메서드 하나가 끝날 때마다 초기화를 해줘야한다.
  @AfterEach
  public void afterEach(){
    repo.clearStore();
  }

  @Test //test하기위한 annotation
  public void save() {
    Member member = new Member();
    member.setName("Kimdj");

    repo.save(member);

    Member result = repo.findById(member.getId()).get();    //타입이 optional인데 optional은 .get()으로 가져올 수 있다.
    assertThat(member).isEqualTo(result);   //아래 assertions와 다른 클래스


//    Assertions.assertEquals(member, result);    //비교할 수 있는 메서드, member(Expected)가 기대값 ,result(actual)이 비교값
//    System.out.println("result는 " + (result == member));
  }

  @Test
  public void findByName() {

    Member member1 = new Member();
    member1.setName("Kimdj");
    repo.save(member1);

    Member member2 = new Member();
    member2.setName("KimDongJun");
    repo.save(member2);

    Member result = repo.findByName("Kimdj").get();

    assertThat(result).isEqualTo(member1);

  }

  @Test
  public void findAll(){
    Member member1 = new Member();
    member1.setName("Kimdj");
    repo.save(member1);

    Member member2 = new Member();
    member2.setName("KimDongJun");
    repo.save(member2);

    List<Member> result = repo.findAll();
    assertThat(result.size()).isEqualTo(2);

  }




}
