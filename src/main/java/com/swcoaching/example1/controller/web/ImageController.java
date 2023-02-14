package com.swcoaching.example1.controller.web;

import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.UserResponseDto;
import com.swcoaching.example1.service.file.UploadFileService;
import com.swcoaching.example1.service.team.TeamService;
import com.swcoaching.example1.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
@RestController
public class ImageController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UploadFileService uploadFileService;
    private final TeamService teamService;
    private final UserService userService;

    @ResponseBody
    @PostMapping(value = "/api/v1/images", produces = "application/text; charset=utf8")
    public String uploadImage(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("uploadImage");
        String root = "C:/Users/Jeongyeop/teamBuildingBoard/src/main/resources/static";

        return uploadFileService.uploadFile(file, root);
    }

    @ResponseBody
    @PostMapping(value = "/api/v1/team-images/{teamId}", produces = "application/text; charset=utf8")
    public String uploadTeamImage(HttpServletRequest request, @PathVariable Long teamId) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("uploadImage");

        return uploadFileService.uploadTeamFile(file, teamId);
    }

    @ResponseBody
    @PostMapping(value = "/api/v1/user-images/{userId}", produces = "application/text; charset=utf8")
    public String uploadUserImage(HttpServletRequest request, @PathVariable Long userId) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("uploadImage");

        return uploadFileService.uploadUserFile(file, userId);
    }

    @ResponseBody
    @GetMapping(value = "/team-images/{teamId}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getTeamImage(@PathVariable Long teamId) {
        logger.info("image team id=" + teamId);
        TeamResponseDto dto = teamService.findById(teamId);

        byte[] result = Base64.getDecoder().decode(dto.getImage());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(result);
    }

    @ResponseBody
    @GetMapping(value = "/user-images/{userId}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getUserImage(@PathVariable Long userId) {
        logger.info("image user id=" + userId);
        UserResponseDto dto = userService.findById(userId);

        byte[] result = dto.getImage();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(result);
    }
}
