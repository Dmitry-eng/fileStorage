package com.storage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "file")
@Data
@ToString(exclude = {"fileGroupsListEntity"})
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
public class File extends StandardEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;

    @JsonIgnore
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileGroup> fileGroupsListEntity;

}
