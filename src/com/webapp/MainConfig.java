package com.webapp;

import com.webapp.storage.SqlStorage;
import com.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainConfig {
    private static final File PROP_FILE = new File("config\\resumes.properties");
    private static final MainConfig INSTANCE = new MainConfig();

    private final File storageDir;
    private final Storage storage;

    public static MainConfig get() {
        return INSTANCE;
    }

    private MainConfig() {
        try (InputStream inputStream = new FileInputStream(PROP_FILE)) {
            Properties prop = new Properties();
            prop.load(inputStream);
            storageDir = new File(prop.getProperty("storage.dir"));
            storage = new SqlStorage(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROP_FILE.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }
}
