package capstone.roomof.DTO.MemberDTO;


import capstone.roomof.DTO.TokenInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginResponseDTO {

    private String loginId;
    private TokenInfo token;

}
