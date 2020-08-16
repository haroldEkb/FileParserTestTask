package com.haroldekb.FileTransferTestTask.service;

import com.haroldekb.FileTransferTestTask.entity.ParsedFile;
import com.haroldekb.FileTransferTestTask.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author haroldekb@mail.ru
 **/

@Service
public class FileParseService {

    private final FileRepository repository;

    @Autowired
    public FileParseService(FileRepository repository) {
        this.repository = repository;
    }

    public int saveFile(MultipartFile file) {
        ParsedFile parsedFile = new ParsedFile();
        parsedFile.setName(file.getOriginalFilename());
        try {
            parsedFile.setContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository.save(parsedFile).getId();
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void updateFile(Integer id, MultipartFile file) {
        ParsedFile toUpdate = repository.findById(id).get();
        toUpdate.setName(file.getOriginalFilename());
        try {
            toUpdate.setContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(Integer id) {
        repository.deleteById(id);
    }
}
