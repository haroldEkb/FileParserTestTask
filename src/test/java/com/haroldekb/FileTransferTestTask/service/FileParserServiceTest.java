package com.haroldekb.FileTransferTestTask.service;

import com.haroldekb.FileTransferTestTask.entity.UploadedFile;
import com.haroldekb.FileTransferTestTask.repository.FileRepository;
import com.haroldekb.FileTransferTestTask.utils.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author haroldekb@mail.ru
 **/

@ExtendWith(MockitoExtension.class)
public class FileParserServiceTest {

    @Mock
    private FileRepository repository;

    @Mock
    private FileParser parser;

    @Mock
    private MultipartFile multipartFile;

    private FileParseService service;

    @BeforeEach
    void setUp() {
        this.service = new FileParseService(repository, parser);
    }

    @DisplayName("должен вызвать метод save репозитория")
    @Test
    void save() {
        when(repository.save(any())).thenReturn(file());
        service.saveFile(multipartFile);

        verify(repository).save(any());
    }

    @DisplayName("должен сработать метод updateFile")
    @Test
    void updateFile() {
        when(repository.findById(anyInt())).thenReturn(optional());
        service.updateFile(11, multipartFile);
        verify(repository).save(any());
    }

    @DisplayName("должен сработать метод existsById репозитория")
    @Test
    void existsById() {
        service.existsById(11);
        verify(repository).existsById(anyInt());
    }

    @DisplayName("должен сработать метод deleteById репозитория")
    @Test
    void delete() {
        service.deleteFileById(13);
        verify(repository).deleteById(anyInt());
    }

    @DisplayName("должен сработать метод getFileContentById")
    @Test
    void getFileContent() {
        when(repository.findById(anyInt())).thenReturn(optional());
        Assert.isTrue(Arrays.equals(service.getFileContentById(11), file().getContent()), "Content is invalid");
    }

    @DisplayName("должен сработать метод getFileData")
    @Test
    void getFileData() {
        when(repository.findById(anyInt())).thenReturn(optional());
        when(repository.existsById(anyInt())).thenReturn(true);
        service.getFileData(11);
        verify(parser).getFileData(any());
    }

    private UploadedFile file(){
        return new UploadedFile(1, "Test.txt", new byte[]{1,2,3});
    }

    private Optional<UploadedFile> optional(){
        return Optional.of(file());
    }

}
