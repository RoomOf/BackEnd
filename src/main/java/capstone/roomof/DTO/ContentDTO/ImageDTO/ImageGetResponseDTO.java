package capstone.roomof.DTO.ContentDTO.ImageDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageGetResponseDTO {

    private List<String> imageUrls;

}
