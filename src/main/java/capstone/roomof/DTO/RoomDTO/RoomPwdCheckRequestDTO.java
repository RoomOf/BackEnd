package capstone.roomof.DTO.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomPwdCheckRequestDTO {

    private Long roomId;
    private String roomPwd;

}
