package capstone.roomof.repository;


import capstone.roomof.domain.Member;
import capstone.roomof.domain.Room;
import capstone.roomof.domain.content.Sound;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SoundRepository {

    private final EntityManager em;

    public void save(Sound sound){
        em.persist(sound);
    }

    public void delete(Sound sound){
        Assert.notNull(sound, "Entity must not be null!");

        Sound found = em.find(Sound.class, sound.getUrl());
        em.remove(found);
    }
    //전체 음성 조회
    public List<Sound> findAllSounds(Long roomId){
        return em.createQuery("select m from Sound m where m.roomId =: roomId", Sound.class)
                .setParameter("room_id", roomId)
                .getResultList();
    }

    //음성삭제
    public Sound findSound(String soundUrl){
        Sound found = em.find(Sound.class, soundUrl);
        return found;
    }

}
