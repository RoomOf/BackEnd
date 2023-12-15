package capstone.roomof.DTO.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinRequestDTO {

    private String loginId;
    private String loginPwd;
    private String name;
    private String email;

}
