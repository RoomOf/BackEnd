package capstone.roomof.DTO.ContentDTO.VideoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoGetResponseDTO {

    private List<String> videoUrls;

}
