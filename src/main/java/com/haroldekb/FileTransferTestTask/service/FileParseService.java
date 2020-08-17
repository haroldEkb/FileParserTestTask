package com.haroldekb.FileTransferTestTask.service;

import com.haroldekb.FileTransferTestTask.entity.FileData;
import com.haroldekb.FileTransferTestTask.entity.UploadedFile;
import com.haroldekb.FileTransferTestTask.repository.FileRepository;
import com.haroldekb.FileTransferTestTask.utils.FileParser;
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
    private final FileParser parser;

    @Autowired
    public FileParseService(FileRepository repository, FileParser parser) {
        this.repository = repository;
        this.parser = parser;
    }

    public int saveFile(MultipartFile multipartFile) {
        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setName(multipartFile.getOriginalFilename());
        try {
            uploadedFile.setContent(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository.save(uploadedFile).getId();
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void updateFile(Integer id, MultipartFile multipartFile) {
        UploadedFile toUpdate = repository.findById(id).get();
        toUpdate.setName(multipartFile.getOriginalFilename());
        try {
            toUpdate.setContent(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(Integer id) {
        repository.deleteById(id);
    }

    public FileData getFileData(Integer id) {
        if (!repository.existsById(id)) {
            return new FileData();
        }
        return parser.getFileData(getFileContentById(id));
    }

    public byte[] getFileContentById(Integer id) {
        return repository.findById(id).get().getContent();
    }
}
