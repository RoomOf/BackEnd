package capstone.roomof.controller;

import capstone.roomof.DTO.BaseResponse;
import capstone.roomof.DTO.MemberDTO.*;
import capstone.roomof.DTO.TokenInfo;
import capstone.roomof.exception.MemberException.InvalidMemberPwdException;
import capstone.roomof.exception.MemberException.MemberEmailDuplicateException;
import capstone.roomof.exception.MemberException.MemberIdDuplicateException;
import capstone.roomof.exception.MemberException.NoExistingIdException;
import capstone.roomof.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping(value = "/member/join")
    @ResponseBody
    public BaseResponse<MemberJoinResponseDTO> join(@RequestBody MemberJoinRequestDTO memberJoinRequestDTO) {

        MemberJoinResponseDTO memberJoinResponseDTO;
        try{
            memberJoinResponseDTO = new MemberJoinResponseDTO(memberService.join(memberJoinRequestDTO));

        }catch(MemberIdDuplicateException e1){
            return new BaseResponse(e1.getErrorCode());
        }catch(MemberEmailDuplicateException e2){
            return new BaseResponse(e2.getErrorCode());
        }

        return new BaseResponse(memberJoinResponseDTO);
    }

    //로그인
    @PostMapping(value = "/member/login")
    @ResponseBody
    public BaseResponse<MemberLoginResponseDTO> login(@RequestBody MemberLoginRequestDTO memberLoginRequestDTO) {

        TokenInfo token;
        MemberLoginResponseDTO memberLoginResponseDTO;

        try{
            token = memberService.login(memberLoginRequestDTO);
            memberLoginResponseDTO = new MemberLoginResponseDTO(memberLoginRequestDTO.getLoginId(), token);

        }catch(NoExistingIdException e1){
            return new BaseResponse(e1.getErrorCode());
        }catch(InvalidMemberPwdException e2){
            return new BaseResponse(e2.getErrorCode());
        }

        return new BaseResponse(memberLoginResponseDTO);
    }

    //정보조회
    @PostMapping(value = "/member/info")
    @ResponseBody
    public BaseResponse<MemberInfoResponseDTO> getInfo(@RequestBody MemberInfoRequestDTO memberInfoRequestDTO) {

        MemberInfoResponseDTO memberInfoResponseDTO;
        try{
            memberInfoResponseDTO = memberService.getInfo(memberInfoRequestDTO);
        }catch(NoExistingIdException e1){
            return new BaseResponse(e1.getErrorCode());
        }

        return new BaseResponse(memberInfoResponseDTO);
    }

    //회원탈퇴
    @DeleteMapping(value = "/member/unregister")
    @ResponseBody
    public BaseResponse<MemberUnregisterResponseDTO> unregister(@RequestBody MemberUnregisterRequestDTO memberUnregisterRequestDTO) {

        MemberUnregisterResponseDTO memberUnregisterResponseDTO;
        try{
            memberUnregisterResponseDTO  = memberService.unregister(memberUnregisterRequestDTO);
        }catch(NoExistingIdException e1){
            return new BaseResponse(e1.getErrorCode());
        }

        return new BaseResponse(memberUnregisterResponseDTO);
    }

}
