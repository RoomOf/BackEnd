package capstone.roomof.service;

import capstone.roomof.DTO.ContentDTO.ImageDTO.ImageDeleteRequestDTO;
import capstone.roomof.DTO.ContentDTO.ImageDTO.ImageSetThumbnailRequestDTO;
import capstone.roomof.DTO.ContentDTO.ImageDTO.ImageUploadRequestDTO;
import capstone.roomof.DTO.ContentDTO.SoundDTO.SoundDeleteRequestDTO;
import capstone.roomof.DTO.ContentDTO.TTSSoundDTO.TTSSoundDeleteRequestDTO;
import capstone.roomof.DTO.ContentDTO.TTSSoundDTO.TTSSoundGenerateRequestDTO;
import capstone.roomof.DTO.ContentDTO.SoundDTO.SoundUploadRequestDTO;
import capstone.roomof.DTO.ContentDTO.TTSSoundDTO.TTSSoundGenerateResponseDTO;
import capstone.roomof.DTO.ContentDTO.TTSSoundDTO.TTSSoundsDTO;
import capstone.roomof.DTO.ContentDTO.TextDTO.TextDeleteRequestDTO;
import capstone.roomof.DTO.ContentDTO.TextDTO.TextSearchRequestDTO;
import capstone.roomof.DTO.ContentDTO.TextDTO.TextWriteRequestDTO;
import capstone.roomof.DTO.ContentDTO.VideoDTO.VideoDeleteRequestDTO;
import capstone.roomof.DTO.ContentDTO.VideoDTO.VideoUploadRequestDTO;
import capstone.roomof.DTO.RoomDTO.*;
import capstone.roomof.domain.Room;
import capstone.roomof.domain.content.*;
import capstone.roomof.exception.ErrorCode;
import capstone.roomof.exception.RoomException.RoomDeleteException;
import capstone.roomof.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    
    private final RoomRepository roomRepository;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;
    private final SoundRepository soundRepository;
    private final TTSSoundRepository ttsSoundRepository;
    private final TextRepository textRepository;
    private final FileUploader fileUploader;

    //생성 후 room_id 반환
    @Transactional
    public RoomMakeResponseDTO make(RoomMakeRequestDTO roomMakeRequestDTO, List<MultipartFile> images, List<MultipartFile> videos, List<MultipartFile> sounds){

        Room room = new Room();
        room.setMemberId(roomMakeRequestDTO.getMemberId());

        String tempRoomPwd = roomMakeRequestDTO.getRoomPwd().trim();
        room.setPwd(tempRoomPwd);
        if(tempRoomPwd == ""){
            room.setIfPublic(false);
        }else{
            room.setIfPublic(true);
        }
        room.setName(roomMakeRequestDTO.getName());
        room.setBirthDate(roomMakeRequestDTO.getBirthDate());
        room.setDeadDate(roomMakeRequestDTO.getDeadDate());
        room.setRelationship(roomMakeRequestDTO.getRelationship());
        roomRepository.save(room);

        ImageUploadRequestDTO imageUploadRequestDTO = new ImageUploadRequestDTO(roomMakeRequestDTO.getMemberId(), room.getRoomId());
        VideoUploadRequestDTO videoUploadRequestDTO = new VideoUploadRequestDTO(roomMakeRequestDTO.getMemberId(), room.getRoomId());
        SoundUploadRequestDTO soundUploadRequestDTO = new SoundUploadRequestDTO(roomMakeRequestDTO.getMemberId(), room.getRoomId());

        List<String> imageUrls = new ArrayList<>();
        List<String> videoUrls = new ArrayList<>();
        List<String> soundUrls = new ArrayList<>();
        System.out.println(images.size() + "asdf");
        if(images != null){
            imageUrls = fileUploader.makeRoomImage(images, imageUploadRequestDTO);
        }
        if(videos != null) {
            videoUrls = fileUploader.makeRoomVideo(videos, videoUploadRequestDTO);
        }
        if(sounds != null) {
            soundUrls = fileUploader.makeRoomSound(sounds, soundUploadRequestDTO);
        }
        RoomMakeResponseDTO roomMakeResponseDTO = new RoomMakeResponseDTO(room.getRoomId(), imageUrls, videoUrls, soundUrls);

        return roomMakeResponseDTO;
    }

    public RoomDeleteResponseDTO delete(RoomDeleteRequestDTO roomDeleteRequestDTO){
        Room found = roomRepository.findOne(roomDeleteRequestDTO.getRoomId());
        if(found.getMemberId() != roomDeleteRequestDTO.getMemberId()){
            throw new RoomDeleteException("방을 생성한 사람만이 삭제할 수 있습니다.", ErrorCode.NOT_ROOM_OWNER);
        }
        if(found.getPwd() != roomDeleteRequestDTO.getRoomPwd()){
            throw new RoomDeleteException("잘못된 비밀번호입니다.", ErrorCode.INVALID_ROOMPWD);
        }
        roomRepository.delete(found);

        return new RoomDeleteResponseDTO("방을 삭제하였습니다.");
    }

    //멤버가 만든 방 조회
    public RoomSearchByOwnerResponseDTO findByOwner(RoomSearchByOwnerRequestDTO roomSearchByOwnerRequestDTO){

        List<Room> rooms = roomRepository.findAllByOwner(roomSearchByOwnerRequestDTO.getMemberId());
        List<Long> roomIds = new ArrayList<Long>();
        for(Room room : rooms){
            roomIds.add(room.getRoomId());
        }
        return new RoomSearchByOwnerResponseDTO(roomIds);
    }

    //전체 방 조회
    public RoomSearchResponseDTO findAll(){
        List<Room> rooms = roomRepository.findAll();
        List<RoomSearchResponse> foundRooms = new ArrayList<>();
        for(Room room : rooms){
            RoomSearchResponse roomsearchResponse = new RoomSearchResponse();
            roomsearchResponse.setName(room.getName());
            roomsearchResponse.setId(room.getRoomId());
            roomsearchResponse.setBirthDate(room.getBirthDate());
            roomsearchResponse.setIfPublic(room.isIfPublic());
            foundRooms.add(roomsearchResponse);
        }
        return new RoomSearchResponseDTO(foundRooms);
    }


    //이름으로 방 조회
    public RoomSearchResponseDTO findByName(String name){

        List<Room> rooms = roomRepository.findAllByName(name);
        List<RoomSearchResponse> foundRooms = new ArrayList<>();
        for(Room room : rooms){
            RoomSearchResponse roomsearchResponse = new RoomSearchResponse();
            roomsearchResponse.setName(room.getName());
            roomsearchResponse.setId(room.getRoomId());
            roomsearchResponse.setBirthDate(room.getBirthDate());
            roomsearchResponse.setIfPublic(room.isIfPublic());
            foundRooms.add(roomsearchResponse);
        }
        return new RoomSearchResponseDTO(foundRooms);
    }

    //id로 객체 반환
    public String findById(Long roomId) {
        Room found = roomRepository.findOne(roomId);
        return found.getName();
    }

    public boolean checkPwd(RoomPwdCheckRequestDTO roomPwdCheckRequestDTO){
        Room room = roomRepository.findOne(roomPwdCheckRequestDTO.getRoomId());
        //System.out.println("/" + room.getPwd() + "/ /" + roomPwdCheckRequestDTO.getRoomPwd() + "/");
        return (roomPwdCheckRequestDTO.getRoomPwd().equals(room.getPwd()));
    }

    ////////////////////////////////////////////사진
    //사진추가
    public String addImage(MultipartFile file, ImageUploadRequestDTO imageUploadRequestDTO){
        Image image = new Image();
        image.setUrl(fileUploader.uploadImageFile(file, imageUploadRequestDTO));
        image.setRoomId(imageUploadRequestDTO.getRoomId());
        image.setIfThumbnail(false);
        image.setIfBackground(false);
        imageRepository.save(image);
        return image.getUrl();
    }
    //사진삭제
    public boolean deleteImage(ImageDeleteRequestDTO imageDeleteRequestDTO){
        boolean ifSuccess = false;
        Image image = imageRepository.findImage(imageDeleteRequestDTO.getImageUrl());
        ifSuccess = imageRepository.deleteImage(image);
        return ifSuccess;
    }
    //thumbnail 이미지 정하기
    public String setThumbnail(ImageSetThumbnailRequestDTO imageSetThumbnailRequestDTO){
        List<Image> images = imageRepository.findAllImages(imageSetThumbnailRequestDTO.getRoomId());
        for(Image image : images){
            image.setIfThumbnail(false);
        }
        Image image = imageRepository.findImage(imageSetThumbnailRequestDTO.getImageUrl());
        image.setIfThumbnail(true);
        return image.getUrl();
    }
    public String getThumbnail(Long roomId){
        Image image = imageRepository.findThumbnail(roomId);
        return image.getUrl();
    }

    //배경 이미지 정하기
    public String setBackground(Long room_id, String image_url){
        Room room = roomRepository.findOne(room_id);
        Image image = imageRepository.findImage(image_url);
        room.setBackground(image.getUrl());
        return image.getUrl();
    }

    //랜덤으로 사진 주기
    public List<String> getRandomImages(Long roomId){
        List<Image> images = imageRepository.findAllImages(roomId);

        List<String> imageUrl = new ArrayList<>();
        for(Image image : images){ imageUrl.add(image.getUrl()); }

        Collections.shuffle(imageUrl);

        return imageUrl;
    }

    //전체 사진 반환
    public List<String> getAllImages(Long roomId){
        List<Image> images = imageRepository.findAllImages(roomId);
        List<String> image_urls = new ArrayList<>();

        for(Image image : images){ image_urls.add(image.getUrl()); }
        return image_urls;
    }
    ////////////////////////////////////////////사진
    ////////////////////////////////////////////영상
    //영상추가
    public String addVideo(MultipartFile file, VideoUploadRequestDTO videoUploadRequestDTO){
        Video video = new Video();
        video.setUrl(fileUploader.uploadVideoFile(file, videoUploadRequestDTO));
        video.setRoomId(videoUploadRequestDTO.getRoomId());
        videoRepository.save(video);
        return video.getUrl();
    }

    //전체 영상 반환
    public List<String> getAllVideos(Long room_id){
        List<Video> videos = videoRepository.findAllVideos(room_id);

        List<String> video_urls = new ArrayList<>();

        for(Video video : videos){ video_urls.add(video.getUrl()); }
        return video_urls;
    }
    //랜덤으로 영상 주기
    public List<String> getRandomVideos(Long roomId){
        List<Video> videos = videoRepository.findAllVideos(roomId);

        List<String> videoUrl = new ArrayList<>();
        for(Video video : videos){ videoUrl.add(video.getUrl()); }

        Collections.shuffle(videoUrl);

        return videoUrl;
    }

    //영상삭제
    public boolean deleteVideo(VideoDeleteRequestDTO videoDeleteRequestDTO){
        boolean ifSuccess = false;
        Video video = videoRepository.findVideo(videoDeleteRequestDTO.getVideoUrl());
        ifSuccess = videoRepository.deleteVideo(video);
        return ifSuccess;
    }
    ////////////////////////////////////////////영상
    ////////////////////////////////////////////음성
    //음성 추가
    public String addSound(MultipartFile file, SoundUploadRequestDTO soundUploadRequestDTO){
        Sound sound = new Sound();
        sound.setUrl(fileUploader.uploadSoundFile(file, soundUploadRequestDTO));
        sound.setRoomId(soundUploadRequestDTO.getRoomId());
        soundRepository.save(sound);
        return sound.getUrl();
    }

    //전체 음성 반환
    public List<String> getAllSounds(Long room_id){
        List<Sound> sounds = soundRepository.findAllSounds(room_id);

        List<String> sound_urls = new ArrayList<>();

        for(Sound sound : sounds){ sound_urls.add(sound.getUrl()); }
        return sound_urls;
    }

    //음성 삭제
    public void deleteSound(SoundDeleteRequestDTO soundDeleteRequestDTO){
        Sound sound = soundRepository.findSound(soundDeleteRequestDTO.getSoundUrl());
        soundRepository.delete(sound);
    }
    ////////////////////////////////////////////음성
    ////////////////////////////////////////////TTS음성
    //전체 음성 반환
    public TTSSoundGenerateResponseDTO generateSound(TTSSoundGenerateRequestDTO TTSSoundGenerateRequestDTO) throws IOException {
        ByteToMultipartFileConverter converter = new ByteToMultipartFileConverter();
        String title;
        if(TTSSoundGenerateRequestDTO.getText().length() >= 10){
            title = TTSSoundGenerateRequestDTO.getText().substring(0, 9) + "...";
        }else{
            title = TTSSoundGenerateRequestDTO.getText();
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp); // 생성한 timestamp 출력
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy MM/dd HH:mm");
        String formatedNow = sdf.format(timestamp);
        System.out.println(formatedNow);
        String ttsSoundUrl;
        System.out.println(TTSSoundGenerateRequestDTO.getText());
        //요청쏴서 받기
        MultipartFile ttsSoundFile;
        String apiUrl = "http://52.79.190.201:9000/voice";// TTSSoundGenerateRequestDTO.getText();
        String requestBody = "{\"text\": \"" + TTSSoundGenerateRequestDTO.getText() + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, byte[].class);

        byte[] result = responseEntity.getBody();

        //배열을 MultipartFile로 변환
        ttsSoundFile = converter.convert(result, TTSSoundGenerateRequestDTO.getRoomId()+".wav");

        //S3 업로드
        ttsSoundUrl = fileUploader.uploadTTSSoundFile(ttsSoundFile);
        //객체 만들기
        TTSSound ttsSound = new TTSSound();
        ttsSound.setRoomId(TTSSoundGenerateRequestDTO.getRoomId());
        ttsSound.setGeneratedDate(formatedNow);
        ttsSound.setTitle(title);
        ttsSound.setContent(TTSSoundGenerateRequestDTO.getText());
        ttsSound.setUrl(ttsSoundUrl);
        ttsSoundRepository.save(ttsSound);

        TTSSoundGenerateResponseDTO ttsSoundGenerateResponseDTO = new TTSSoundGenerateResponseDTO();
        ttsSoundGenerateResponseDTO.setGeneratedDate(ttsSound.getGeneratedDate());
        ttsSoundGenerateResponseDTO.setUrl(ttsSoundUrl);
        ttsSoundGenerateResponseDTO.setTitle(ttsSound.getTitle());
        ttsSoundGenerateResponseDTO.setContent(ttsSound.getContent());

        return ttsSoundGenerateResponseDTO;
    }
    private static String extractFirstSentence(String input) {
        // 마침표, 물음표, 느낌표 등을 기준으로 문자열을 분리
        String[] sentences = input.split("[.!?]");

        // 첫 번째 문장 반환
        if (sentences.length > 0) {
            return sentences[0].trim(); // 앞뒤 공백 제거
        } else {
            return ""; // 문장이 없을 경우 빈 문자열 반환
        }
    }

    //전체 음성 반환
    public List<TTSSoundsDTO> getAllTTSSound(Long roomId){
        List<TTSSound> ttsSounds = ttsSoundRepository.findAllTTSSounds(roomId);

        List<TTSSoundsDTO> ttsSoundsDTOs = new ArrayList<>();

        for(TTSSound ttsSound : ttsSounds){
            TTSSoundsDTO ttsSoundsDTO = new TTSSoundsDTO();
            ttsSoundsDTO.setTitle(ttsSound.getTitle());
            ttsSoundsDTO.setContent(ttsSound.getContent());
            ttsSoundsDTO.setUrl(ttsSound.getUrl());
            ttsSoundsDTO.setGeneratedDate(ttsSound.getGeneratedDate());
            ttsSoundsDTOs.add(ttsSoundsDTO);
        }
        return ttsSoundsDTOs;
    }

    //음성 삭제
    public void deleteTTSSound(TTSSoundDeleteRequestDTO ttsSoundDeleteRequestDTO){
        TTSSound ttsSound = ttsSoundRepository.findTTSSound(ttsSoundDeleteRequestDTO.getTtsSoundUrl());
        ttsSoundRepository.delete(ttsSound);
    }
    ////////////////////////////////////////////TTS음성
    ////////////////////////////////////////////방명록
    //방명록 추가
    public Long writeText(TextWriteRequestDTO textWriteRequestDTO){
        Text text = new Text();
        text.setRoomId(textWriteRequestDTO.getRoomId());
        text.setContent(textWriteRequestDTO.getContent());
        text.setWriterId(textWriteRequestDTO.getWriterId());
        text.setWrittenDate(textWriteRequestDTO.getWrittenDate());
        textRepository.save(text);

        return text.getId();
    }

    //방명록삭제
    public boolean deleteText(TextDeleteRequestDTO textDeleteRequestDTO){

        Text text = textRepository.findText(textDeleteRequestDTO.getTextId());
        if(text.getWriterId().equals(textDeleteRequestDTO.getWriterId()));
            textRepository.deleteText(text);

        if(textRepository.findText(textDeleteRequestDTO.getTextId()) == null)
            return true;
        else
            return false;
    }

    public List<Text> searchText(TextSearchRequestDTO textSearchRequestDTO){

        if(textSearchRequestDTO.isIfMine()){
            return textRepository.findMyTexts(textSearchRequestDTO.getRoomId(), textSearchRequestDTO.getMemberId());
        }else{
            return textRepository.findAllTexts(textSearchRequestDTO.getRoomId());
        }
    }

}
