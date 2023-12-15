package capstone.roomof.repository;

import capstone.roomof.domain.Member;
import capstone.roomof.domain.Room;
import capstone.roomof.domain.content.Image;
import capstone.roomof.domain.content.Text;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TextRepository {

    private final EntityManager em;

    public void save(Text text){
        em.persist(text);
    }

    //방명록 조회
    public Text findText(Long textId){
        Text found = em.find(Text.class, textId);
        return found;
    }

    //방명록 삭제
    public void deleteText(Text text){
        Assert.notNull(text, "Entity must not be null!");

        Text found = em.find(Text.class, text.getId());
        em.remove(found);
    }
    //전체 방명록 조회
    public List<Text> findAllTexts(Long roomId){
        return em.createQuery("select m from Text m where m.roomId =: roomId", Text.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }

    // 전체 방명록 조회
    public List<Text> findMyTexts(Long roomId, String writerId) {
        return em.createQuery("select m from Text m where m.roomId =:roomId and m.writerId =:writerId", Text.class)
                .setParameter("roomId", roomId)
                .setParameter("writerId", writerId)
                .getResultList();
    }

}
