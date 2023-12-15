package capstone.roomof.DTO.ContentDTO.SoundDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SoundGetResponseDTO {
    private List<String> soundUrls;
}

