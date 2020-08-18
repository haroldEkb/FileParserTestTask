package com.haroldekb.FileTransferTestTask.entity;

import com.haroldekb.FileTransferTestTask.structure.TreeFileStructure;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author haroldekb@mail.ru
 **/

public class FileData {

    /**
    * Изначально написал класс, реализующий древовидную структуру,
    * где каждый узел хранит номер строки начала раздела,
    * уровень вложенности (глубину) раздела и список своих подразделов.
    * Затем решил, что это слишком сложно для работы на клиенте.
    * В итоге остановился на LinkedHashMap<Integer, Integer>,
    * где ключ - номер строки начала раздела,
    * значение - глубина раздела
    **/

    private TreeFileStructure structure;
    private List<String> content;

    public FileData() {
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public TreeFileStructure getStructure() {
        return structure;
    }

    public void setStructure(TreeFileStructure structure) {
        this.structure = structure;
    }
}
