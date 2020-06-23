package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "file")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "info", nullable = false)
    private String info;

    @Column(name = "data" , nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "open", nullable = false)
    private boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileGroup> fileGroupsList;

    public List<FileGroup> getFileGroupsList() {
        return fileGroupsList;
    }

    public void setFileGroupsList(List<FileGroup> fileGroupsList) {
        this.fileGroupsList = fileGroupsList;
    }

    public Files() {
    }
    public Files(String name, String info, Date data, User user, String location, Boolean open){
            this.name=name;
            this.info=info;
            this.data=data;
            this.user=user;
            this.location=location;
            this.open=open;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Files files1 = (Files) o;
        return open == files1.open &&
                Objects.equals(id, files1.id) &&
                Objects.equals(name, files1.name) &&
                Objects.equals(info, files1.info) &&
                Objects.equals(data, files1.data) &&
                Objects.equals(user, files1.user) &&
                Objects.equals(location, files1.location) &&
                Objects.equals(fileGroupsList, files1.fileGroupsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, info, data, location, open, fileGroupsList);
    }


    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", data=" + data +
                ", user=" + user +
                ", location='" + location + '\'' +
                ", open=" + open +
                '}';
    }
}
