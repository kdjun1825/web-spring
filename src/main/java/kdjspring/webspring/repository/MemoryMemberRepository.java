package kdjspring.webspring.repository;

import kdjspring.webspring.domain.Member;

import java.util.*;

//MemberRepository 를 가져와서 사용
public class MemoryMemberRepository implements  MemberRepository{

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;  //key값을 생성역할

  //저장
  @Override
  public Member save(Member member) {

    member.setId(++sequence);           //sequence값을 id로 set
    store.put(member.getId(), member);  //store라는 Map에 set 한 id 와 받은 member 저장
    return member;                      //member return

  }


  @Override
  public Optional<Member> findById(long id) {
    return Optional.ofNullable(store.get(id));  //optional 클래스의 ofnullable로 null이어도 처리 가능.
  }


  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
            .filter(member -> member.getName().equals(name)).findAny();
    //loof로 돌면서 누구든 찾으면 반환 끝까지 못찾으면 optional안의 null로 반환

  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values()); //values는 member들
  }

  //test코드에서 쓸 초기화 메서드
  public void clearStore(){
    store.clear();
  }

}
