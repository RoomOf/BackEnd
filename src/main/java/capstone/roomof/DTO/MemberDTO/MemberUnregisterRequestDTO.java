package capstone.roomof.DTO.MemberDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUnregisterRequestDTO {

    private String loginId;
    private String loginPwd;
}
