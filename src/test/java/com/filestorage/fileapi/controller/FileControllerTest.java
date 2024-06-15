package com.filestorage.fileapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import com.filestorage.fileapi.service.FileService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    @Test
    public void testUploadFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("files", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

        mockMvc.perform(multipart("/files/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("파일 업로드 성공"));
    }

    @Test
    public void testGetFiles() throws Exception {
        mockMvc.perform(get("/files"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFile() throws Exception {
        String filename = "test.txt";

        mockMvc.perform(delete("/files/delete/" + filename))
                .andExpect(status().isOk())
                .andExpect(content().string("파일 삭제 성공"));
    }

}
