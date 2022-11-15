package com.storage.service.admin;

import com.storage.model.Group;
import com.storage.repository.GroupRepository;
import com.storage.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupAdminImpl implements GroupAdmin {
    private final GroupRepository groupRepository;
    @Override
    public List<Group> list() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> list(String id) {
       return Util.getGroup(id, list());
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

}
