package com.swcoaching.example1.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {
    String uploadFile(MultipartFile file, String path) throws IOException;
}
