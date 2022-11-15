package com.storage.service.admin;

import com.storage.model.File;

import java.util.List;

public interface FileAdmin {
    List<File> list();
    List<File> list(String id);
}
