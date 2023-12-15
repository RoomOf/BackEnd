package capstone.roomof.DTO.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomSearchByOwnerResponseDTO {

    private List<Long> roomIds;

}
