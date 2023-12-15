package capstone.roomof.domain.content;

import capstone.roomof.domain.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Sound {

    @Id
    private String url;

    @JoinColumn(name = "roomId")
    private Long roomId;
}
