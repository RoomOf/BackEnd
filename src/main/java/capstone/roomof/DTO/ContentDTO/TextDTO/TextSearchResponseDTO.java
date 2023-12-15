package capstone.roomof.DTO.ContentDTO.TextDTO;

import capstone.roomof.domain.content.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextSearchResponseDTO {

    private List<Text> texts;
}
