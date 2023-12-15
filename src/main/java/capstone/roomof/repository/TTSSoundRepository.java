package capstone.roomof.repository;


import capstone.roomof.domain.Member;
import capstone.roomof.domain.Room;
import capstone.roomof.domain.content.Image;
import capstone.roomof.domain.content.Sound;
import capstone.roomof.domain.content.TTSSound;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TTSSoundRepository {

    private final EntityManager em;

    public void save(TTSSound ttsSound){
        em.persist(ttsSound);
    }

    public void delete(TTSSound ttsSound){
        Assert.notNull(ttsSound, "Entity must not be null!");

        TTSSound found = em.find(TTSSound.class, ttsSound.getUrl());
        em.remove(found);
    }
    //전체 음성 조회
    public List<TTSSound> findAllTTSSounds(Long roomId){
        return em.createQuery("select m from TTSSound m where m.roomId =: roomId", TTSSound.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }

    //음성삭제
    public TTSSound findTTSSound(String ttsSoundUrl){
        TTSSound found = em.find(TTSSound.class, ttsSoundUrl);
        return found;
    }

}
