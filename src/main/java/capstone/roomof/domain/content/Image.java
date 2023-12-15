package capstone.roomof.domain.content;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Image {

    @Id
    private String url;

    @JoinColumn(name = "roomId")
    private Long roomId;

    private boolean ifThumbnail;

    private boolean ifBackground;

}
