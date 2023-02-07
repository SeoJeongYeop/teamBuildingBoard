package com.swcoaching.example1.service.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public String uploadFile(MultipartFile upFile, String path) throws IOException {
        String originalFilename = upFile.getOriginalFilename();
        System.out.println("originalFilename: " + originalFilename);
        long fileSize = upFile.getSize();
        System.out.println("fileSize: " + fileSize);
        if (originalFilename != null) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String uniqueName = "/images/" + uuids[0] + fileExtension;
            System.out.println("saved File=" + path + uniqueName);
            FileOutputStream stream = new FileOutputStream(path + uniqueName);
            stream.write(upFile.getBytes());
            stream.close();
            return uniqueName;
        }
        return null;
    }
}
