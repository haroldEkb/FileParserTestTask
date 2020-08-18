package com.haroldekb.FileTransferTestTask.utils;

import com.haroldekb.FileTransferTestTask.entity.FileData;
import com.haroldekb.FileTransferTestTask.structure.TreeFileStructure;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author haroldekb@mail.ru
 **/

@Component
public class FileParser {

    public FileData getFileData(byte[] content) {
        FileData fileData = new FileData();
        List<String> list = fileContentToStrings(content);
        fileData.setContent(list);
        TreeFileStructure structure = new TreeFileStructure(list);
        fileData.setStructure(structure);
        return fileData;
    }

    public List<String> fileContentToStrings(byte[] bytes){
        List<String> list = new LinkedList<>();
        try {
            BufferedReader stream = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
            while (stream.ready()){
                list.add(stream.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(list);
    }
}
