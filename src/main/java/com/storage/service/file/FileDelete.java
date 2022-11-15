package com.storage.service.file;

import com.storage.model.File;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.FileUtils;

@AllArgsConstructor
public class FileDelete {
    private final String dir;

    @SneakyThrows
    public final void delete(File files) {
        String directory = files.getLocation();
        int number = directory.lastIndexOf("\\");
        java.io.File file = new java.io.File(dir + directory.substring(0, number));
        FileUtils.forceDelete(file);
    }
}
