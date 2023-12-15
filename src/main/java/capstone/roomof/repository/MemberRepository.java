package capstone.roomof.repository;

import capstone.roomof.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final PasswordEncoder passwordEncoder;


    //회원가입
    public void save(Member member){
        //member.setLoginPwd(bcript.encrypt(member.getLoginPwd()));
        member.setLoginPwd(passwordEncoder.encode(member.getLoginPwd()));
        em.persist(member);
    }

    //id로 Member객체 반환
    public Member findByLoginId(String loginId){
        return em.find(Member.class, loginId);
    }

    public Optional<Member> findByMemberId(String loginId) {
        Optional<Member> member = Optional.ofNullable(em.find(Member.class, loginId));
        return member;
    }
    //회원삭제
    public void delete(Member member){
        Assert.notNull(member, "Entity must not be null!");

        Member found = em.find(Member.class, member.getLoginId());
        em.remove(found);
    }

    //로그인 id로 객체 반환
    public List<Member> findAllByLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId =: loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

    //이메일로 객체 반환
    public List<Member> findAllByEmail(String email){
        return em.createQuery("select m from Member m where m.email =: email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

}
