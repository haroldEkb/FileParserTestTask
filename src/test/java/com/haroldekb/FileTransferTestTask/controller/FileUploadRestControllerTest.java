package com.haroldekb.FileTransferTestTask.controller;

import com.haroldekb.FileTransferTestTask.entity.FileData;
import com.haroldekb.FileTransferTestTask.service.FileParseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author haroldekb@mail.ru
 **/

@WebMvcTest(FileUploadRestController.class)
public class FileUploadRestControllerTest {

    @MockBean
    private FileParseService service;

    @Autowired
    private MockMvc mvc;

    MockMultipartFile multipartFile;

    @BeforeEach
    public void setup(){
        multipartFile = new MockMultipartFile("file", "Test.txt", "text/plain", "12345".getBytes());
    }

    @DisplayName("должен возвращать GET /files/{id}")
    @Test
    public void getFileDataById() throws Exception {
        when(service.existsById(anyInt())).thenReturn(true);
        when(service.getFileData(anyInt()))
                .thenReturn(new FileData());

        mvc.perform(get("/files/21"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"structure\": null, \"content\": null}"));
    }

    @DisplayName("должен выполнять POST /files")
    @Test
    public void savePost() throws Exception {
        when(service.saveFile(any()))
                .thenReturn(31);
        mvc.perform(multipart("/files").file(multipartFile))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\": 31}"));
    }

    @DisplayName("должен обновить файл через PUT /files/{id}")
    @Test
    public void updatePut() throws Exception {
        when(service.existsById(anyInt())).thenReturn(true);

        mvc.perform(multipart("/files/11").file(multipartFile).with(request -> {
            request.setMethod("PUT");
            return request;
        })).andExpect(status().isOk());
    }

    @DisplayName("должен сохранить файл через PUT /files/{id}")
    @Test
    public void savePut() throws Exception {
        when(service.existsById(anyInt())).thenReturn(false);
        when(service.saveFile(any())).thenReturn(11);

        mvc.perform(multipart("/files/11").file(multipartFile).with(request -> {
            request.setMethod("PUT");
            return request;
        }))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("location", "localhost:8083/file-parser/files/11"));
    }

    @DisplayName("должен выполнять DELETE /files/{id}")
    @Test
    public void deleteById() throws Exception {
        when(service.existsById(anyInt())).thenReturn(true);

        mvc.perform(delete("/files/1"))
                .andExpect(status().isOk());
    }
}
