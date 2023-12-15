package capstone.roomof.DTO.ContentDTO.TTSSoundDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TTSSoundGenerateResponseDTO {

    private String title;

    private String content;

    private String generatedDate;

    private String url;
}
