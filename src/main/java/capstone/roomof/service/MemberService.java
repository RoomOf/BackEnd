package capstone.roomof.service;

import capstone.roomof.DTO.MemberDTO.*;
import capstone.roomof.DTO.TokenInfo;
import capstone.roomof.domain.Member;
import capstone.roomof.exception.ErrorCode;
import capstone.roomof.exception.MemberException.MemberEmailDuplicateException;
import capstone.roomof.exception.MemberException.MemberIdDuplicateException;
import capstone.roomof.exception.MemberException.NoExistingIdException;
import capstone.roomof.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    @Transactional
    public String join(MemberJoinRequestDTO memberJoinRequestDTO){
        Member member = new Member();

        member.setLoginId(memberJoinRequestDTO.getLoginId());
        member.setLoginPwd(memberJoinRequestDTO.getLoginPwd());

        member.setName(memberJoinRequestDTO.getName());
        member.setEmail(memberJoinRequestDTO.getEmail());
        member.getRoles().add("USER");

        validateDuplicateMemberByLoginId(member);
        validateDuplicateMemberByEmail(member);

        memberRepository.save(member);

        return member.getLoginId();
    }

    //로그인
    @Transactional
    public TokenInfo login(MemberLoginRequestDTO memberLoginRequestDTO) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(memberLoginRequestDTO.getLoginId(), memberLoginRequestDTO.getLoginPwd());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    //본인 정보 조회
    public MemberInfoResponseDTO getInfo(MemberInfoRequestDTO memberInfoRequestDTO){

        Member member = memberRepository.findByLoginId(memberInfoRequestDTO.getLoginId());
        MemberInfoResponseDTO memberInfoResponseDTO
                = new MemberInfoResponseDTO(member.getLoginId(), member.getName(), member.getEmail());

        return memberInfoResponseDTO;
    }

    //회원탈퇴
    public MemberUnregisterResponseDTO unregister(MemberUnregisterRequestDTO memberUnregisterRequestDTO){
            Member found = memberRepository.findByLoginId(memberUnregisterRequestDTO.getLoginId());
            if(found == null){
                throw new NoExistingIdException("사용자를 찾을 수 없습니다.", ErrorCode.ID_NOT_FOUND);
            }
            memberRepository.delete(found);

            return new MemberUnregisterResponseDTO("성공적으로 탈퇴하였습니다.");
    }

    ////Validation////

    private void searchMemberByLoginId(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findAllByLoginId(member.getLoginId());
        if(!findMembers.isEmpty()){
            throw new NoExistingIdException("사용자를 찾을 수 없습니다.", ErrorCode.ID_NOT_FOUND);
        }
    }

    //중복 Id 존재 여부 판단
    private void validateDuplicateMemberByLoginId(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findAllByLoginId(member.getLoginId());
        if(!findMembers.isEmpty()){
            throw new MemberIdDuplicateException("이미 사용중인 Id입니다.", ErrorCode.ID_DUPLICATED);
        }
    }

    //중복 email 존재 여부 판단
    private void validateDuplicateMemberByEmail(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findAllByEmail(member.getEmail());
        if(!findMembers.isEmpty()){
            throw new MemberEmailDuplicateException("이미 사용중인 이메일입니다.", ErrorCode.EMAIL_DUPLICATED);
        }
    }

}
