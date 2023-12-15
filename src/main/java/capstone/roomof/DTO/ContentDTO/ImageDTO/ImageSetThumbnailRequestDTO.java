package capstone.roomof.DTO.ContentDTO.ImageDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageSetThumbnailRequestDTO {

    private String memberId;
    private Long roomId;
    private String imageUrl;

}
