package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    //@Rollback(value = false) -> TestCase인 경우 commit된 값들을 Rollback함(TestCase가 DB에 반영되면 곤란하기 때문)
    //영속성 플러시가 되지 않아서
    @Test
    public void RegistrationTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);


        //then
        Assertions.assertThat(member).isSameAs(memberRepository.findOne(savedId));
    }

    @Test
    public void DuplicateMemberException() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); //여기서 예외 발생 필요
        } catch (IllegalStateException e){
            return;
        }
        //then
        //예외가 안터지면 여기에 도달
        org.junit.jupiter.api.Assertions.fail("예외가 발생해야 한다.");
    }

}