package com.example.demo.service.file;

import com.example.demo.model.Files;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileDelete {
    private String str;

    public FileDelete(@Value("${address.file}") String str) {
        this.str = str;
    }

    public final void delete(Files files) {
        String directory = files.getLocation();
        int number = directory.lastIndexOf("\\");
        File file = new File(str + directory.substring(0, number));
        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
