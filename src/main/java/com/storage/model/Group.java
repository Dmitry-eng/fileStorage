package com.storage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "\"group\"")
@Data
@ToString(exclude = {"files", "files", "accounts", "requests"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Group extends StandardEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_account",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> accounts;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Request> requests;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "file_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private List<File> files;

    @Transient
    private  GroupStatus status;

    public Group(String name, Account account) {
        this.name = name;
        this.account = account;
    }
}
