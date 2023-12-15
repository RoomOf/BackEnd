package capstone.roomof.controller;

import capstone.roomof.DTO.BaseResponse;
import capstone.roomof.DTO.RoomDTO.*;
import capstone.roomof.exception.ContentException.ImageUploadException;
import capstone.roomof.exception.ContentException.SoundUploadException;
import capstone.roomof.exception.ContentException.VideoUploadException;
import capstone.roomof.exception.MemberException.NoExistingIdException;
import capstone.roomof.exception.RoomException.RoomDeleteException;
import capstone.roomof.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    //방 생성
    @PostMapping("/room/make")
    @ResponseBody
    public BaseResponse<RoomMakeResponseDTO> make(@RequestPart RoomMakeRequestDTO roomMakeRequestDTO, @RequestPart(required = false) List<MultipartFile> images,
                                                  @RequestPart(required = false) List<MultipartFile> videos, @RequestPart(required = false) List<MultipartFile> sounds) {

        RoomMakeResponseDTO roomMakeResponseDTO;
        try{
            System.out.println(images.size());
            roomMakeResponseDTO = roomService.make(roomMakeRequestDTO, images, videos, sounds);
        }catch(ImageUploadException e1){
            return new BaseResponse(e1.getErrorCode());
        }catch(VideoUploadException e2){
            return new BaseResponse(e2.getErrorCode());
        }catch(SoundUploadException e3){
            return new BaseResponse(e3.getErrorCode());
        }

        return new BaseResponse(roomMakeResponseDTO);
    }

    //사람 이름으로 방 조회
    @GetMapping(value = "/room/search")
    @ResponseBody
    public BaseResponse<RoomSearchResponseDTO> searchByName(@RequestParam String name) {

        name = name.trim();

        RoomSearchResponseDTO roomSearchResponseDTO;
        if(name == "")//전체 방 검색
            roomSearchResponseDTO = roomService.findAll();
        else//이름으로 조회
            roomSearchResponseDTO = roomService.findByName(name);

        return new BaseResponse(roomSearchResponseDTO);
    }

    //현재 회원이 만든 방 조회
    @PostMapping("/room/search")
    @ResponseBody
    public BaseResponse<RoomSearchByOwnerResponseDTO> searchByOwner(@RequestBody RoomSearchByOwnerRequestDTO roomSearchByOwnerRequestDTO) {

        RoomSearchByOwnerResponseDTO roomSearchByOwnerResponseDTO;

        try{
            roomSearchByOwnerResponseDTO =roomService.findByOwner(roomSearchByOwnerRequestDTO);
        }catch(NoExistingIdException e1) {
            return new BaseResponse(e1.getErrorCode());
        }

        return new BaseResponse(roomSearchByOwnerResponseDTO);
    }

    @PostMapping("/room/enter")
    @ResponseBody
    public BaseResponse<RoomPwdCheckResponseDTO> checkRoomPwd(@RequestBody RoomPwdCheckRequestDTO roomPwdCheckRequestDTO) {

        boolean ifCorrect = roomService.checkPwd(roomPwdCheckRequestDTO);
        RoomPwdCheckResponseDTO roomPwdCheckResponseDTO = new RoomPwdCheckResponseDTO(ifCorrect);

        return new BaseResponse(roomPwdCheckResponseDTO);
    }

    //방 삭제
    @DeleteMapping("/room/delete")
    @ResponseBody
    public BaseResponse<RoomDeleteResponseDTO> delete(@RequestBody RoomDeleteRequestDTO roomDeleteRequestDTO) {

        RoomDeleteResponseDTO roomDeleteResponseDTO;
        try{
            roomDeleteResponseDTO = roomService.delete(roomDeleteRequestDTO);
        }catch(RoomDeleteException e1){
            return new BaseResponse(e1.getErrorCode());
        }

        return new BaseResponse(roomDeleteResponseDTO);
    }

    @GetMapping(value = "/room/info")
    @ResponseBody
    public BaseResponse<RoomInfoResponseDTO> getRoomInfo(@RequestParam Long roomId) {

        RoomInfoResponseDTO roomInfoResponseDTO = new RoomInfoResponseDTO(roomService.findById(roomId));

        return new BaseResponse(roomInfoResponseDTO);
    }
}
