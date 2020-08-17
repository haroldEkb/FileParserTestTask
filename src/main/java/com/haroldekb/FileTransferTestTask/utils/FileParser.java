package com.haroldekb.FileTransferTestTask.utils;

import com.haroldekb.FileTransferTestTask.entity.FileData;
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
        LinkedHashMap<Integer, Integer> structure = getFileStructure(list);
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

    private LinkedHashMap<Integer, Integer> getFileStructure(List<String> list) {
        LinkedHashMap<Integer, Integer> structure = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).startsWith("#")){
                structure.put(i, getDepth(list.get(i)));
            }
        }
        return structure;
    }

    private Integer getDepth(String s) {
        int i = 0;
        char ch = s.charAt(i);
        while (ch == '#') {
            i++;
            ch = s.charAt(i);
        }
        return i;
    }
}
