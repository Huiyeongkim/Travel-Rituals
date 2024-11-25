/*
package travel.travel.image.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import travel.travel.common.dto.CommonResDto;
import travel.travel.image.dto.ImageResDto;
import travel.travel.image.service.ImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ImageResDto dto = imageService.uploadFile(multipartFile);
        new ResponseEntity<>(new CommonResDto(HttpStatus.OK, "이미지업로드가 성공적으로 되었습니다.", dto), HttpStatus.OK);
    }
}
*/
