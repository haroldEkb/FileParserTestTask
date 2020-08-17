package com.haroldekb.FileTransferTestTask.repository;

import com.haroldekb.FileTransferTestTask.entity.UploadedFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<UploadedFile, Integer> {
}
