package capstone.roomof.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class ByteToMultipartFileConverter {

    public MultipartFile convert(byte[] bytes, String fileName) throws IOException {
        // 바이트 배열을 ByteArrayResource로 변환
        ByteArrayResource resource = new ByteArrayResource(bytes);

        // ByteArrayResource를 사용하여 MultipartFile 생성
        return new CustomMultipartFile(resource, fileName);
    }
}

class CustomMultipartFile implements MultipartFile {

    private final ByteArrayResource byteArrayResource;
    private final String fileName;

    public CustomMultipartFile(ByteArrayResource byteArrayResource, String fileName) {
        this.byteArrayResource = byteArrayResource;
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public String getContentType() {
        return MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }

    @Override
    public boolean isEmpty() {
        return byteArrayResource.contentLength() == 0;
    }

    @Override
    public long getSize() {
        return byteArrayResource.contentLength();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return byteArrayResource.getInputStream().readAllBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return byteArrayResource.getInputStream();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        // Optional: If transferTo is needed, implement this method accordingly
    }

    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        // Optional: If transferTo is needed, implement this method accordingly
    }
}
