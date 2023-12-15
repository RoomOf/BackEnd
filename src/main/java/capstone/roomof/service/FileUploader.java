package capstone.roomof.service;

import capstone.roomof.DTO.ContentDTO.ImageDTO.ImageUploadRequestDTO;
import capstone.roomof.DTO.ContentDTO.SoundDTO.SoundUploadRequestDTO;
import capstone.roomof.DTO.ContentDTO.VideoDTO.VideoUploadRequestDTO;
import capstone.roomof.domain.content.Image;
import capstone.roomof.domain.content.Sound;
import capstone.roomof.domain.content.Video;
import capstone.roomof.exception.ContentException.ImageUploadException;
import capstone.roomof.exception.ContentException.VideoUploadException;
import capstone.roomof.exception.ErrorCode;
import capstone.roomof.repository.ImageRepository;
import capstone.roomof.repository.SoundRepository;
import capstone.roomof.repository.VideoRepository;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class FileUploader {

    private final AmazonS3Client amazonS3Client;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;
    private final SoundRepository soundRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    //사진 업로드
    public List<String> makeRoomImage(List<MultipartFile> images, ImageUploadRequestDTO imageUploadRequestDTO) {

        List<String> imageUrls = new ArrayList<>();
        System.out.println(images.size());
        String ImageBucket = bucket + "/Image";
        images.forEach(image -> {
            MultipartFile file = image;
            String fileName = file.getOriginalFilename() + UUID.randomUUID().toString();
            try {
                ObjectMetadata metadata= new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());
                amazonS3Client.putObject(ImageBucket,fileName,file.getInputStream(),metadata);

                Image img = new Image();
                img.setUrl(amazonS3Client.getUrl(ImageBucket, fileName).toString());
                img.setRoomId(imageUploadRequestDTO.getRoomId());
                img.setIfThumbnail(false);
                img.setIfBackground(false);
                imageRepository.save(img);

                imageUrls.add(img.getUrl());
            } catch (IOException e) {
                throw new ImageUploadException("Image Upload Fail", ErrorCode.IMAGE_UPLOAD_FAIL);
            }
        });
        return imageUrls;
    }

    public List<String> makeRoomVideo(List<MultipartFile> videos, VideoUploadRequestDTO videoUploadRequestDTO) {

        List<String> videoUrls = new ArrayList<>();
        String VideoBucket = bucket + "/Video";
        videos.forEach(video -> {
            MultipartFile file = video;
            String fileName = file.getOriginalFilename() + UUID.randomUUID().toString();
            try {
                ObjectMetadata metadata= new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());
                amazonS3Client.putObject(VideoBucket,fileName,file.getInputStream(),metadata);

                Video video1 = new Video();
                video1.setRoomId(videoUploadRequestDTO.getRoomId());
                video1.setUrl(amazonS3Client.getUrl(VideoBucket, fileName).toString());
                videoRepository.save(video1);

                videoUrls.add(video1.getUrl());
            } catch (IOException e) {
                throw new VideoUploadException("Video Upload Fail", ErrorCode.VIDEO_UPLOAD_FAIL);
            }
        });
        return videoUrls;
    }

    public List<String> makeRoomSound(List<MultipartFile> sounds, SoundUploadRequestDTO soundUploadRequestDTO) {

        List<String> soundUrls = new ArrayList<>();
        String SoundBucket = bucket + "/Sound";
        sounds.forEach(sound -> {
            MultipartFile file = sound;
            String fileName = file.getOriginalFilename() + UUID.randomUUID().toString();
            try {
                ObjectMetadata metadata= new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());
                amazonS3Client.putObject(SoundBucket,fileName,file.getInputStream(),metadata);

                Sound sound1 = new Sound();
                sound1.setRoomId(soundUploadRequestDTO.getRoomId());
                sound1.setUrl(amazonS3Client.getUrl(SoundBucket, fileName).toString());
                soundRepository.save(sound1);

                soundUrls.add(sound1.getUrl());
            } catch (IOException e) {
                throw new VideoUploadException("Sound Upload Fail", ErrorCode.VIDEO_UPLOAD_FAIL);
            }
        });
        return soundUrls;
    }

    public String uploadImageFile(MultipartFile image, ImageUploadRequestDTO imageUploadRequestDTO) {
        try {
            MultipartFile file = image;
            String fileName = file.getOriginalFilename() + UUID.randomUUID().toString();

            String ImageBucket = bucket + "/Image";
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(ImageBucket,fileName,file.getInputStream(),metadata);

            return amazonS3Client.getUrl(ImageBucket, fileName).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String uploadVideoFile(MultipartFile video, VideoUploadRequestDTO videoUploadRequestDTO) {
        try {
            MultipartFile file = video;
            String fileName = file.getOriginalFilename() + UUID.randomUUID().toString();

            String ImageBucket = bucket + "/Video";
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(ImageBucket,fileName,file.getInputStream(),metadata);

            return amazonS3Client.getUrl(ImageBucket, fileName).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String uploadSoundFile(MultipartFile sound, SoundUploadRequestDTO soundUploadRequestDTO) {
        try {
            MultipartFile file = sound;
            String fileName = file.getOriginalFilename() + UUID.randomUUID().toString();

            String SoundBucket = bucket + "/Sound";
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(SoundBucket,fileName,file.getInputStream(),metadata);

            return amazonS3Client.getUrl(SoundBucket, fileName).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String uploadTTSSoundFile(MultipartFile ttsSound) {
        try {
            MultipartFile file = ttsSound;
            String fileName =  UUID.randomUUID().toString() +file.getOriginalFilename();

            String TTSSoundBucket = bucket + "/TTSSound";
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(TTSSoundBucket,fileName,file.getInputStream(),metadata);

            return amazonS3Client.getUrl(TTSSoundBucket, fileName).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}