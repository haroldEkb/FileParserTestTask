package com.haroldekb.FileTransferTestTask.controller;

import com.haroldekb.FileTransferTestTask.service.FileParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

/**
 * @author haroldekb@mail.ru
 **/
@RestController
public class FileUploadRestController {

    private final FileParseService service;

    @Autowired
    public FileUploadRestController(FileParseService service) {
        this.service = service;
    }

    @PostMapping("/files")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: File is empty");
        }
        int fileId;
        fileId = service.saveFile(file);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Success: file " + file.getOriginalFilename() + " is saved. Id = " + fileId);

    }

    @PutMapping("/files/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: File is empty");
        }
        if (service.existsById(id)) {
            service.updateFile(id, file);
            return ResponseEntity.ok().build();
        } else {
            int i = service.saveFile(file);
            return ResponseEntity.created(URI.create("localhost:8083/file-parser/files/" + i )).build();
        }
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        if (service.existsById(id)) {
            service.deleteFile(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
