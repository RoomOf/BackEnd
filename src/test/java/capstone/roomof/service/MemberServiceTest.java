//package capstone.roomof.service;
//
//import capstone.roomof.domain.Member;
//import capstone.roomof.repository.MemberRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)//Junit 을 스프링과 함께 실행할래
//@SpringBootTest//
//@Transactional//rollback
//public class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Rollback(false)
//    public void register() throws Exception{
//        Member member = new Member();
//        member.setLoginId("capstone1");
//
//        String savedId = memberService.join(member);
//
//        assertEquals(member, memberRepository.findByLoginId(savedId));
//
//    }
//
//    @Test(expected = DataIntegrityViolationException.class)
//    public void  중복_ID_예외() throws Exception{
//
//        Member member1 = new Member();
//        member1.setLoginId("capstone1");
//
//        Member member2 = new Member();
//        member2.setLoginId("capstone1");
//
//        memberService.join(member1);
//        memberService.join(member2);
//
//        fail("예외가 발생해야 한다.");//오면 잘못된거다. 오면안됀다. 이 전에 Exception이 발생해야 한다.
//    }
//
//}