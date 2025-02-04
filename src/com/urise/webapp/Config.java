package com.urise.webapp;

import com.urise.webapp.sql.SqlHelper;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO: 21.02.2023 Привет по поводу того вопроса
//getHomeDir() + /out/artifacts/basejava/basejava.war/WEB-INF/classes/resumes.properties - в конфиге
//а Project_Dir как VM Option при запуске

public class Config {
    private static final File PROPS = new File("C:\\Users\\nikita\\Desktop\\GitHub\\basejava\\config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private final File storageDir;
    private final Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage(new SqlHelper(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password")));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file! " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    private static File getHomeDir() {
        String prop = System.getProperty("homeDir");
        File homeDir = new File(prop == null ? "." : prop);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + "is not directory");
        }
        return homeDir;
    }

    public Storage getStorage() {
        return storage;
    }
}
