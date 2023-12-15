package capstone.roomof.DTO.ContentDTO.TTSSoundDTO;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TTSSoundsDTO {

    private String title;

    private String content;

    private String generatedDate;

    private String url;

}
