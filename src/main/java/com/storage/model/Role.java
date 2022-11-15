package com.storage.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "role")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends StandardEntity implements GrantedAuthority {
    @Column(nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
