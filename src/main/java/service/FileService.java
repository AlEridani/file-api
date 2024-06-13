package service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    void saveFile(MultipartFile file);
    List<String> listFiles();
    Resource loadFile(String filename);
    void deleteFile(String filename);
}
