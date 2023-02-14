package com.swcoaching.example1.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {
    String uploadFile(MultipartFile file, String path) throws IOException;
    String uploadTeamFile(MultipartFile file, Long teamId) throws IOException;
    String uploadUserFile(MultipartFile file, Long userId) throws IOException;
}
