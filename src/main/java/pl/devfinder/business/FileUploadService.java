package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.devfinder.domain.exception.FileUploadToProfileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class FileUploadService {
    private final Environment environment;
    public String getUploadDir(String uuid, String filename) {
        String userDataPath = environment.getProperty("devfinder-conf.user-data-path", String.class);

        assert userDataPath != null;
        Path uploadPath = Paths.get(userDataPath);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return uploadPath + File.separator + uuid + filename;
    }
    public String saveFileToDisc(String uuid, MultipartFile file) {

        try {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            String uploadDir = getUploadDir(uuid, filename);
            log.info("Process copy file to [{}] ", uploadDir);

            Files.copy(file.getInputStream(), Paths.get(uploadDir), StandardCopyOption.REPLACE_EXISTING);

            return filename;

        } catch (IOException e) {
            throw new FileUploadToProfileException(
                    "Could not save file to disc: [%s]".formatted(file.getOriginalFilename()));
        }
    }

    public void deleteFileFromDisc(String uuid, String filename) {
        try {
            log.info("Process delete file from disc, direction: [{}]", getUploadDir(uuid, filename));
            Files.deleteIfExists(Paths.get(getUploadDir(uuid, filename)));
        } catch (IOException e) {
            throw new FileUploadToProfileException("Could not delete file from disc");
        }
    }
    public boolean oldFileExist(String uuid, String filename) throws IOException {
        log.info("Checked if file exist in direction: [{}]", getUploadDir(uuid, filename));
        return Files.exists(Paths.get(getUploadDir(uuid, filename)));
    }
}
