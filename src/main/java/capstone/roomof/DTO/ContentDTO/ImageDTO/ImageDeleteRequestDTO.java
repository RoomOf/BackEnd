package capstone.roomof.DTO.ContentDTO.ImageDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDeleteRequestDTO {
    private String memberId;
    private String roomId;
    private String imageUrl;
}
