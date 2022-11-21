package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\nikita\\Desktop\\GitHub\\basejava\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File dir = new File("C:\\Users\\nikita\\Desktop\\GitHub\\basejava\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        for (String name : Objects.requireNonNull(dir.list())) {
            System.out.println(name);
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
