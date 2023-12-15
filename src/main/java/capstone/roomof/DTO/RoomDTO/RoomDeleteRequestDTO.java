package capstone.roomof.DTO.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomDeleteRequestDTO {
    private String memberId;
    private Long roomId;
    private String roomPwd;
}
