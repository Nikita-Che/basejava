package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.io.File;
import java.util.UUID;

import static com.urise.webapp.ResumeTestData.createResume;

public class TestData {
    public static final File STORAGE_DIR = Config.get().getStorageDir();

    public static final String UUID_1 = String.valueOf(UUID.randomUUID());
    public static final String UUID_2 = String.valueOf(UUID.randomUUID());
    public static final String UUID_3 = String.valueOf(UUID.randomUUID());
    public static final String UUID_4 = String.valueOf(UUID.randomUUID());
    public static final String FULL_NAME1 = "Vasya";
    public static final String FULL_NAME2 = "Petya";
    public static final String FULL_NAME3 = "Kolya";
    public static final String FULL_NAME4 = "Grisha";
    public static final String UUID_NOT_EXIST = "dummy";
    public static final Resume RESUME_1 = createResume(UUID_1, FULL_NAME1);
    public static final Resume RESUME_2 = createResume(UUID_2, FULL_NAME2);
    public static final Resume RESUME_3 = createResume(UUID_3, FULL_NAME3);
    public static final Resume RESUME_4 = createResume(UUID_4, FULL_NAME4);
    public static final Resume RESUME_5 = createResume(UUID_NOT_EXIST, FULL_NAME1);

}
