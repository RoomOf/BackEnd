package capstone.roomof.controller;

import capstone.roomof.DTO.BaseResponse;
import capstone.roomof.DTO.ContentDTO.ImageDTO.*;
import capstone.roomof.DTO.ContentDTO.SoundDTO.*;
import capstone.roomof.DTO.ContentDTO.TTSSoundDTO.*;
import capstone.roomof.DTO.ContentDTO.TextDTO.*;
import capstone.roomof.DTO.ContentDTO.VideoDTO.*;
import capstone.roomof.domain.content.Text;
import capstone.roomof.exception.ErrorCode;
import capstone.roomof.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ContentController {

    private final RoomService roomService;


    ///////////    ///////////    ///////////    ///////////    /////////// Image
    //image 업로드
    @PostMapping("/room/image/upload")
    @ResponseBody
    public BaseResponse<ImageUploadResponseDTO> uploadImage(
            @RequestPart(value = "image") MultipartFile file,
            @RequestPart ImageUploadRequestDTO imageUploadRequestDTO){

        ImageUploadResponseDTO imageUploadResponseDTO = new ImageUploadResponseDTO(roomService.addImage(file, imageUploadRequestDTO));

        return new BaseResponse(imageUploadResponseDTO);
    }

    //image 삭제
    @DeleteMapping("/room/image/delete")
    @ResponseBody
    public BaseResponse<ImageDeleteResponseDTO> deleteImage(@RequestBody ImageDeleteRequestDTO imageDeleteRequestDTO){

        boolean ifSuccess = false;
        ifSuccess = roomService.deleteImage(imageDeleteRequestDTO);
        ImageDeleteResponseDTO imageDeleteResponseDTO = new ImageDeleteResponseDTO(ifSuccess);

        return new BaseResponse(imageDeleteResponseDTO);
    }

    //image 조회
    @GetMapping("/room/image")
    @ResponseBody
    public BaseResponse<ImageGetResponseDTO> getImage(@RequestParam String roomId, @RequestParam String ifRandom){
        boolean isRandom = Boolean.parseBoolean(ifRandom);
        ImageGetResponseDTO imageGetResponseDTO;
        if(!isRandom)
            imageGetResponseDTO = new ImageGetResponseDTO(roomService.getAllImages(Long.parseLong(roomId)));
        else
            imageGetResponseDTO = new ImageGetResponseDTO(roomService.getRandomImages(Long.parseLong(roomId)));

        return new BaseResponse(imageGetResponseDTO);
    }

    //thumbnail지정
    @PostMapping("/room/image/thumbnail")
    @ResponseBody
    public BaseResponse<ImageSetThumbnailRequestDTO> setThumbnail(@RequestBody ImageSetThumbnailRequestDTO imageSetThumbnailRequestDTO){

        ImageSetThumbnailResponseDTO imageSetThumbnailResponseDTO
                = new ImageSetThumbnailResponseDTO(roomService.setThumbnail(imageSetThumbnailRequestDTO));

        return new BaseResponse(imageSetThumbnailResponseDTO);
    }
    @GetMapping("/room/image/thumbnail")
    @ResponseBody
    public BaseResponse<ImageGetThumbnailResponseDTO> getThumbnail(@RequestParam Long roomId){

        ImageGetThumbnailResponseDTO imageGetThumbnailResponseDTO
                = new ImageGetThumbnailResponseDTO(roomService.getThumbnail(roomId));

        return new BaseResponse(imageGetThumbnailResponseDTO);
    }

    ///////////    ///////////    ///////////    ///////////    /////////// Image


    ///////////    ///////////    ///////////    ///////////    /////////// Video
    //video 업로드
    @PostMapping("/room/video/upload")
    @ResponseBody
    public BaseResponse<VideoUploadResponseDTO> uploadVideo(
            @RequestPart(value = "video") MultipartFile file,
            @RequestPart VideoUploadRequestDTO videoUploadRequestDTO){

        VideoUploadResponseDTO videoUploadResponseDTO = new VideoUploadResponseDTO(roomService.addVideo(file, videoUploadRequestDTO));

        return new BaseResponse(videoUploadResponseDTO);
    }

    //video 삭제
    @DeleteMapping("/room/video/delete")
    @ResponseBody
    public BaseResponse<VideoDeleteRequestDTO> deleteVideo(@RequestBody VideoDeleteRequestDTO videoDeleteRequestDTO){

        boolean ifSuccess = false;
        ifSuccess = roomService.deleteVideo(videoDeleteRequestDTO);
        VideoDeleteResponseDTO videoDeleteResponseDTO = new VideoDeleteResponseDTO(ifSuccess);

        return new BaseResponse(videoDeleteResponseDTO);
    }

    //video 조회
    @GetMapping("/room/video")
    @ResponseBody
    public BaseResponse<VideoGetResponseDTO> getVideo(@RequestParam String roomId, @RequestParam String ifRandom){
        boolean isRandom = Boolean.parseBoolean(ifRandom);
        VideoGetResponseDTO videoGetResponseDTO;
        if(!isRandom)
            videoGetResponseDTO = new VideoGetResponseDTO(roomService.getAllVideos(Long.parseLong(roomId)));
        else
            videoGetResponseDTO = new VideoGetResponseDTO(roomService.getRandomVideos(Long.parseLong(roomId)));

        return new BaseResponse(videoGetResponseDTO);
    }
    ///////////    ///////////    ///////////    ///////////    /////////// Video

    @PostMapping("/room/text/write")
    @ResponseBody
    public BaseResponse<TextWriteResponseDTO> writeText(@RequestBody TextWriteRequestDTO textWriteRequestDTO){

        Long textId = roomService.writeText(textWriteRequestDTO);
        TextWriteResponseDTO textWriteResponseDTO = new TextWriteResponseDTO(textId);

        return new BaseResponse<>(textWriteResponseDTO);
    }

    @DeleteMapping("/room/text/delete")
    @ResponseBody
    public BaseResponse<TextDeleteResponseDTO> deleteText(@RequestBody TextDeleteRequestDTO textDeleteRequestDTO){

        boolean ifSuccess = false;
        if(roomService.deleteText(textDeleteRequestDTO))
            ifSuccess = true;
        TextDeleteResponseDTO textDeleteResponseDTO = new TextDeleteResponseDTO(ifSuccess);

        return new BaseResponse(textDeleteResponseDTO);
    }

    @PostMapping("/room/text/search")
    @ResponseBody
    public BaseResponse<TextSearchResponseDTO> searchText(@RequestBody TextSearchRequestDTO textSearchRequestDTO){

        List<Text> texts = roomService.searchText(textSearchRequestDTO);
        TextSearchResponseDTO textSearchResponseDTO = new TextSearchResponseDTO(texts);

        return new BaseResponse<>(textSearchResponseDTO);
    }
    ///////////    ///////////    ///////////    ///////////    /////////// Sound
    //video 업로드
    @PostMapping("/room/sound/upload")
    @ResponseBody
    public BaseResponse<SoundUploadRequestDTO> uploadSound(
            @RequestPart(value = "sound") MultipartFile file,
            @RequestPart SoundUploadRequestDTO soundUploadRequestDTO){

        SoundUploadResponseDTO soundUploadResponseDTO = new SoundUploadResponseDTO(roomService.addSound(file, soundUploadRequestDTO));

        return new BaseResponse(soundUploadResponseDTO);
    }

    //video 삭제
    @DeleteMapping("/room/sound/delete")
    @ResponseBody
    public BaseResponse<SoundDeleteResponseDTO> deleteSound(@RequestBody SoundDeleteRequestDTO soundDeleteRequestDTO){

        roomService.deleteSound(soundDeleteRequestDTO);
        SoundDeleteResponseDTO soundDeleteResponseDTO = new SoundDeleteResponseDTO("이미지 삭제");

        return new BaseResponse(soundDeleteResponseDTO);
    }

    //video 조회
    @GetMapping("/room/sound")
    @ResponseBody
    public BaseResponse<SoundGetResponseDTO> getVideo(@RequestParam Long roomId){

        SoundGetResponseDTO soundGetResponseDTO = new SoundGetResponseDTO(roomService.getAllSounds(roomId));

        return new BaseResponse(soundGetResponseDTO);
    }

    ///////////    ///////////    ///////////    ///////////    /////////// Sound

    ///////////    ///////////    ///////////    ///////////    /////////// TTSSound
    @PostMapping("/room/ttssound/generate")
    @ResponseBody
    public BaseResponse<TTSSoundGenerateResponseDTO> generateTTSSound(@RequestBody TTSSoundGenerateRequestDTO ttsSoundGenerateRequestDTO){

        TTSSoundGenerateResponseDTO ttsSoundGenerateResponseDTO = new TTSSoundGenerateResponseDTO();
        try{
            ttsSoundGenerateResponseDTO = roomService.generateSound(ttsSoundGenerateRequestDTO);
        }catch(IOException e){
            return new BaseResponse(ErrorCode.TTS_SOUND_GENERATE_FAIL);
        }

        return new BaseResponse(ttsSoundGenerateResponseDTO);
    }

    @DeleteMapping("/room/ttssound/delete")
    @ResponseBody
    public BaseResponse<TTSSoundDeleteRequestDTO> deleteTTSSound(@RequestBody TTSSoundDeleteRequestDTO ttsSoundDeleteRequestDTO){

        roomService.deleteTTSSound(ttsSoundDeleteRequestDTO);
        TTSSoundDeleteResponseDTO ttsSoundDeleteResponseDTO = new TTSSoundDeleteResponseDTO("TTS 음성 삭제");

        return new BaseResponse(ttsSoundDeleteResponseDTO);
    }

    //video 조회
    @GetMapping("/room/ttssound")
    @ResponseBody
    public BaseResponse<TTSSoundGetResponseDTO> getTTSSound(@RequestParam Long roomId){

        TTSSoundGetResponseDTO ttsSoundGetResponseDTO = new TTSSoundGetResponseDTO(roomService.getAllTTSSound(roomId));

        return new BaseResponse(ttsSoundGetResponseDTO);
    }

    ///////////    ///////////    ///////////    ///////////    /////////// TTSSound

}
