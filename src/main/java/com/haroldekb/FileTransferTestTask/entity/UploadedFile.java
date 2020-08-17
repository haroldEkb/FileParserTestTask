package com.haroldekb.FileTransferTestTask.entity;

import javax.persistence.*;

/**
 * @author haroldekb@mail.ru
 **/

@Entity
@Table(name = "files")
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "content")
    private byte[] content;

    public UploadedFile() {
    }

    public UploadedFile(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public UploadedFile(int id, String name, byte[] content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
