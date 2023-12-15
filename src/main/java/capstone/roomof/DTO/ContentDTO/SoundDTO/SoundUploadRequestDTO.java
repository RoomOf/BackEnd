package capstone.roomof.DTO.ContentDTO.SoundDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SoundUploadRequestDTO {
    private String memberId;
    private Long roomId;
}
