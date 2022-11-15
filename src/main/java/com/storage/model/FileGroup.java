package com.storage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "file_group")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileGroup extends StandardEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Group group;

    public FileGroup(File file, Group group){
        this.file= file;
        this.group = group;
    }
}
