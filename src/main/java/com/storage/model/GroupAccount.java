package com.storage.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group_account")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupAccount extends StandardEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;

    public GroupAccount(Account account, Group group) {
        this.account = account;
        this.group = group;
    }
}
