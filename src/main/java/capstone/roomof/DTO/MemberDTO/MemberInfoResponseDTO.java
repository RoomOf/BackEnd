package capstone.roomof.DTO.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfoResponseDTO {

    private String loginId;
    private String name;
    private String email;

}
