package capstone.roomof.DTO.ContentDTO.SoundDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SoundDeleteRequestDTO {
    private String memberId;
    private String roomId;
    private String soundUrl;
}
