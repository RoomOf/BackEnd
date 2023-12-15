package capstone.roomof.DTO.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomMakeResponseDTO {

    private Long roomId;
    private List<String> imageUrls;
    private List<String> videoUrls;
    private List<String> soundUrls;
}
