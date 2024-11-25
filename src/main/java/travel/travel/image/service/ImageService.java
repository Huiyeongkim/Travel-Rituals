package travel.travel.image.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import travel.travel.image.dto.ImageResDto;
import travel.travel.image.repository.ImageRepository;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Client s3Client;
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ImageResDto uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "-" + originalFilename;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(uniqueFileName)
                .contentType(multipartFile.getContentType())
                .contentLength(multipartFile.getSize())
                .build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize()));
        } catch (Exception e) {
            throw new IOException("S3 업로드 실패: " + e.getMessage(), e);
        }

        String url = s3Client.utilities().getUrl(builder -> builder.bucket(bucket).key(originalFilename)).toString();
        return ImageResDto.builder()
                .imageUrl(url)
                .build();
    }
}
