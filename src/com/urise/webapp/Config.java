package com.urise.webapp;

import com.urise.webapp.sql.SqlHelper;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
//    protected static final File PROPS = new File(getHomeDir(), "\\config\\resumes.properties");
    protected static final File PROPS = new File("C:\\Users\\nikita\\Desktop\\GitHub\\basejava\\config\\resumes.properties");
//    protected static final File PROPS = new File("web\\WEB-INF");
//    protected static final File PROPS = new File("/WEB-INF/classes/resumes.properties");
    private final File storageDir;
    public Properties props = new Properties();
    public Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {

//        try (InputStream is = new FileInputStream(PROPS)) {
        try (InputStream is = new FileInputStream("C:\\Users\\nikita\\Desktop\\GitHub\\basejava\\config\\resumes.properties")) {
//        try (InputStream is = new FileInputStream("web\\WEB-INF")) {
//        try (InputStream is = new FileInputStream("\\opt\\tomcat\\apache-tomcat-9.0.68\\webapps\\resumes\\WEB-INF\\classes\\resumes.properties")) {
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
}
