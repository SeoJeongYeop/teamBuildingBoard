package com.swcoaching.example1.controller.web;


import com.swcoaching.example1.service.file.UploadFileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
public class ImageController {

    private final UploadFileService uploadFileService;

    @ResponseBody
    @PostMapping(value = "/api/v1/images", produces = "application/text; charset=utf8")
    public String uploadImage(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("uploadImage");
        String root = "C:/Users/Jeongyeop/teamBuildingBoard/src/main/resources/static";

        return uploadFileService.uploadFile(file, root);
    }

    @ResponseBody
    @GetMapping(value = "/images/{imageName:.+}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        String root = "C:/Users/Jeongyeop/teamBuildingBoard/src/main/resources/static/images/";
        System.out.println("imageName: " + root + imageName);
        Path path = Paths.get(root + imageName);

        byte[] result = Files.readAllBytes(path);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(result);
    }
}
