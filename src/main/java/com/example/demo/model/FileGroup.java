package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "file_group")
public class FileGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"file\"", nullable = false)
    private Files file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"group\"", nullable = false)
    private Group group;

    public FileGroup(){}
    public FileGroup(Files files, Group group){
        this.file=files;
        this.group=group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Files getFile() {
        return file;
    }

    public void setFile(Files file) {
        this.file = file;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileGroup fileGroup = (FileGroup) o;
        return id == fileGroup.id &&
                Objects.equals(file, fileGroup.file) &&
                Objects.equals(group, fileGroup.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, file, group);
    }
}
