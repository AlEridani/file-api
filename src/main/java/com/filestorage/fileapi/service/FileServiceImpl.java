package com.filestorage.fileapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService{

    @Value("${file.upload-dir}")
    private String uploadDir;


    @Override
    public void saveFile(MultipartFile file) {
        try {
            Path copyLocation = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename() + ". Please try again!", e);
        }
    }

    @Override
    public List<String> listFiles() {
        try {
            return Files.walk(Paths.get(uploadDir), 1)
                    .filter(path -> !path.equals(Paths.get(uploadDir)))
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Could not list files", e);
        }
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            Path file = Paths.get(uploadDir).resolve(filename);
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file: " + filename, e);
        }
    }
}
