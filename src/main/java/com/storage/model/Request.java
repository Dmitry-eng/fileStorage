package com.storage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Request extends StandardEntity{

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Group group;

    public Request(Account account, Group group) {
        this.account = account;
        this.group = group;
    }
}
