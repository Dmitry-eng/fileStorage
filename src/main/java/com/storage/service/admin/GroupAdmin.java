package com.storage.service.admin;

import com.storage.model.Group;

import java.util.List;

public interface GroupAdmin {
    List<Group> list();
    List<Group> list(String value);
    void delete(Long id);
}
