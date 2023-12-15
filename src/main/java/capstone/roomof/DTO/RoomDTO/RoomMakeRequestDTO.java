package capstone.roomof.DTO.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomMakeRequestDTO {

    private String memberId;
    private String roomPwd;
    private String name;
    private String birthDate;
    private String deadDate;
    private String relationship;

}
