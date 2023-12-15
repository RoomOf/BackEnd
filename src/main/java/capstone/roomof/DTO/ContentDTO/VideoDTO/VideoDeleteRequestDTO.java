package capstone.roomof.DTO.ContentDTO.VideoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDeleteRequestDTO {
    private String memberId;
    private String roomId;
    private String videoUrl;
}
