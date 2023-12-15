package capstone.roomof.DTO.ContentDTO.VideoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideoUploadRequestDTO {

    private String memberId;
    private Long roomId;

}
