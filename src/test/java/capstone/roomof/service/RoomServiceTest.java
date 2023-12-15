package capstone.roomof.service;

import capstone.roomof.domain.Room;
import capstone.roomof.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)//Junit 을 스프링과 함께 실행할래
@SpringBootTest//
@Transactional//rollback
public class RoomServiceTest {

    @Autowired
    RoomService roomService;
    @Autowired
    RoomRepository roomRepository;

    @Test
    @Rollback(true)
    public void 방생성() throws Exception{
        Room room = new Room();
        room.setName("deadMan");
        room.setBirthDate(new Date());

        //Long savedId = roomService.make(room);

        //assertEquals(room, roomRepository.findOne(savedId));

    }


}
