package kdjspring.webspring;

import kdjspring.webspring.repository.MemberRepository;
import kdjspring.webspring.repository.MemoryMemberRepository;
import kdjspring.webspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  @Bean
  public MemberService memService(){
    return new MemberService(memRepository()); //호출을해서 빈에 등록을 해야하는데 ctrl+p로 파라미터 필요한 것 확인
  }

  @Bean
  public MemberRepository memRepository() {
    return new MemoryMemberRepository();  //MemberRepository는 인터페이스라서 구현체인 MemoryMemberRepository를 호출
  }

}
