package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//형변환이나 LAZY를 위해서 Transactional을 사용
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //field only
    //생성자가 단 하나일 경우 스프링이 스스로 @Autowired 로 처리해준다.
    //이 생성자를 만들어 주는 것이 Lombok의 @AllArgsConstructor
    //상위 호환이 @RequiredArgsConstructor **RequiredArgsconstructior는 final이 있는 필드만 생성자를 형성해줌
    /**
     public MemberService(MemberRepository memberRepository){
     this.memberRepository = memberRepository;
     }
     */

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    //Transactional(readOnly = true)를 하면 데이터 변경이 아니됨
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    /**
     * 회원 ID로 하나 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
