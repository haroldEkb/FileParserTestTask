package com.haroldekb.FileTransferTestTask.entity;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author haroldekb@mail.ru
 **/

public class FileData {

    /*
    * Изначально написал класс, реализующий древовидную структуру,
    * где каждый узел хранит номер строки начала раздела,
    * уровень вложенности (глубину) раздела и список своих подразделов.
    * Затем решил, что это слишком сложно для работы на клиенте.
    * В итоге остановился на LinkedHashMap<Integer, Integer>,
    * где ключ - номер строки начала раздела,
    * значение - глубина раздела
    */

    private LinkedHashMap<Integer, Integer> structure;
    private List<String> content;

    public FileData() {
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public LinkedHashMap<Integer, Integer> getStructure() {
        return structure;
    }

    public void setStructure(LinkedHashMap<Integer, Integer> structure) {
        this.structure = structure;
    }
}
