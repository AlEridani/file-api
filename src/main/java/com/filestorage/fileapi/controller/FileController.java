package com.filestorage.fileapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.filestorage.fileapi.service.FileService;

import java.util.List;

@RequestMapping("files")
@RestController
public class FileController {
    private final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            fileService.saveFile(file);
        }
        return ResponseEntity.ok("파일 업로드 성공");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getFiles() {
        logger.info("getFiles호출");
        List<String> fileList = fileService.listFiles();
        return ResponseEntity.ok(fileList);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource resource = fileService.loadFile(filename);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        fileService.deleteFile(filename);
        return ResponseEntity.ok("파일 삭제 성공");
    }
}
