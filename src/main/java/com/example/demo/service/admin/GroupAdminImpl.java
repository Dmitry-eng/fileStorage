package com.example.demo.service.admin;

import com.example.demo.model.Group;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.items.Refactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupAdminImpl implements GroupAdmin {
    private GroupRepository groupRepository;
    public GroupAdminImpl(@Autowired GroupRepository groupRepository){
        this.groupRepository=groupRepository;
    }
    @Override
    public List<Group> list() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> list(String id) {
       return Refactor.getGroup(id, list());
    }

    @Override
    public boolean delete(String id) {
        try {
            groupRepository.delete(groupRepository.getOne(Long.parseLong(id)));
            return true;
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }

}
