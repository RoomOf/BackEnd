package capstone.roomof.DTO.ContentDTO.ImageDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadRequestDTO {

    private String memberId;
    private Long roomId;

}
