package com.storage.service.file;

import com.storage.model.Account;
import com.storage.model.File;
//import com.storage.model.FileGroup;
import com.storage.model.Group;
import com.storage.repository.FileRepository;
import com.storage.service.AccountSession;
import com.storage.dto.FileDto;
import com.storage.service.exception.file.FileCode;
import com.storage.service.exception.file.FileException;
import com.storage.service.exception.security.Security;
import com.storage.service.exception.security.SecurityException;
import com.storage.service.group.GroupService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
public class FileServiceImpl implements FileService {
    private static final String LIKE = "%%%s%%";
    private static final String ON = "on";
    private static final Path root = Paths.get("uploads");
    private final FileRepository fileRepository;
    private final AccountSession accountSession;
    private final String directory;
    private final GroupService groupService;

    @Override
    public final List<File> search() {
        return fileRepository.findAllByAccountOrOpenTrue(accountSession.getAccount());
    }

    @Override
    public final List<File> search(String value) {
        return fileRepository.findAllByAccountAndValueContains(accountSession.getAccount(), String.format(LIKE, value));
    }

    @Override
    public final List<File> findFileByAccount() {
        return fileRepository.findAllByAccount(accountSession.getAccount());
    }

    @Override
    public final List<File> findFileByAccount(String value) {
        return fileRepository.findAllByAccount(accountSession.getAccount(), String.format(LIKE, value));
    }

    @Override
    public final void delete(Long id) {
        File file = fileRepository.getOne(id);
        checkAccessDeleteFile(file);
        fileRepository.delete(file);
        removeInStorage(file);
    }

    @Override
    public final void load(MultipartFile multipartFile, FileDto fileDto) {
        com.storage.model.File file = new com.storage.model.File();
        if (multipartFile.isEmpty()) {
            throw new FileException(FileCode.EMPTY);
        }
        Account account = accountSession.getAccount();
        String location = saveLoadFiles(multipartFile, account);
        file.setName(isValidValue(fileDto.getName()) ? fileDto.getName() : multipartFile.getOriginalFilename());
        file.setInfo(isValidValue(fileDto.getInfo()) ? fileDto.getInfo() : "-");
        file.setOpen(ON.equals(fileDto.getCheckbox()));
        file.setAccount(account);
        file.setLocation(location);
        file.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        fileRepository.save(file);
    }

    @SneakyThrows
    @Override
    public final ResponseEntity<Resource> download(Long id) {
        File file = fileRepository.getOne(id);
        checkAccessDownloadFile(file);
        Path pathFile = root.resolve(directory + file.getLocation());
        Resource fileSource = new UrlResource(pathFile.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + fileSource.getFilename() + "\"").body(fileSource);
    }

    private void checkAccessDownloadFile(File file) {
        Account account = accountSession.getAccount();
        if (file.getOpen() || file.getAccount().equals(account) || accountSession.isAdmin()) {
            return;
        }
//        file.getAccount().getActivated().booleanValue()
//        if(account.getGroups().stream().map(Group::getFiles).noneMatch(files -> files.contains(file))) {
//            throw new SecurityException(Security.ACCESS_DENIED);
//        }
    }

    @SneakyThrows
    private void removeInStorage(File files) {
        String directory = files.getLocation();
        int number = directory.lastIndexOf("\\");
        java.io.File file = new java.io.File(this.directory + directory.substring(0, number));
        FileUtils.forceDelete(file);
    }

    @SneakyThrows
    private String saveLoadFiles(MultipartFile file, Account account) {
        String location = account.getId() + "\\" + new java.util.Date().toString().replace(" ", "").replace(":", "");
        byte[] bytes = file.getBytes();
        new java.io.File(directory + location).mkdirs();
        Path path = Paths.get(directory + location, file.getOriginalFilename());
        Files.write(path, bytes);
        return location + "\\" + file.getOriginalFilename();
    }

    private void checkAccessDeleteFile(File file) {
        Account account = accountSession.getAccount();
        if (file.getAccount() != account && !accountSession.isAdmin()) {
            throw new SecurityException(Security.ACCESS_DENIED);
        }
    }

    private boolean isValidValue(String value) {
        return value != null && value.replace(" ", "").length() != 0;
    }


}
