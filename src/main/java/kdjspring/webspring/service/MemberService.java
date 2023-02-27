package kdjspring.webspring.service;

import kdjspring.webspring.domain.Member;
import kdjspring.webspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

  private final MemberRepository repo;

  @Autowired
  //외부에서 MemberRepository를 받아오게 하기 그러면 인스턴스가 같아짐
  public MemberService(MemberRepository repo) {
    this.repo = repo;
  }

  /**
   * 회원가입
   */
  public long join(Member member) {

    //이름 중복 검증
    //로직 안에 다른 로직이 있으면 기능을 나눠놓는 것이 좋다
    // refactor = ctrl + shift + alr + t  ,  바로 method로 빼기 = ctrl + alr + m
    validateDuplicateMember(member);



    repo.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    //    Optional<Member> result = repo.findByName(member.getName());
    //optional로 바로 반환말고 어차피 optional이 반환되기 때문에 바로 ifPresent로 이어갈 수 있음
    repo.findByName(member.getName())
            .ifPresent(m -> {
              throw new IllegalStateException("이미 존재하는 회원입니다.");
    });
    //result member의 값이 있으면 exception던지기
    //ifPresent는 null이 아닌 어떤 값이 있으면 ~~  optional때문에 null이 아닌 한 번 감싸놔서 ifPresent사용 가능
//    result.ifPresent(m -> {
//      throw new IllegalStateException("이미 존재하는 회원입니다.");
//    });

  }

  /**
   * 전체 회원 조회
   * @return
   */
  public List<Member> findMembers(){

    return repo.findAll();

  }


  public Optional<Member> findOne(long memberId) {
    return repo.findById(memberId);
  }


}
