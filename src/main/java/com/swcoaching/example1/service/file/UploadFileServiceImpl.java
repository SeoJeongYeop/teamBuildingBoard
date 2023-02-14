package com.swcoaching.example1.service.file;

import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.domain.team.TeamRepository;
import com.swcoaching.example1.domain.user.User;
import com.swcoaching.example1.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadFileServiceImpl implements UploadFileService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    public String uploadFile(MultipartFile upFile, String path) throws IOException {
        String originalFilename = upFile.getOriginalFilename();
        logger.info("originalFilename: " + originalFilename);
        long fileSize = upFile.getSize();
        logger.info("upload fileSize: " + fileSize);
        if (originalFilename != null) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String uniqueName = "/images/" + uuids[0] + fileExtension;
            logger.info("saved File=" + path + uniqueName);
            FileOutputStream stream = new FileOutputStream(path + uniqueName);
            stream.write(upFile.getBytes());
            stream.close();
            return uniqueName;
        }
        return null;
    }

    @Override
    public String uploadTeamFile(MultipartFile file, Long teamId) throws IOException {

        long fileSize = file.getSize();
        logger.info("team fileSize: " + fileSize);
        Team team = teamRepository.getReferenceById(teamId);
        String imageBase = Base64.getEncoder().encodeToString(file.getBytes());
        team.setImage(imageBase);

        return team.getName();
    }

    @Override
    public String uploadUserFile(MultipartFile file, Long userId) throws IOException {
        long fileSize = file.getSize();
        logger.info("user fileSize: " + fileSize);
        User user = userRepository.getReferenceById(userId);
        String imageBase = Base64.getEncoder().encodeToString(file.getBytes());
        user.setImage(imageBase);

        return user.getName();
    }
}
