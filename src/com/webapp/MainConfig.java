package com.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainConfig {
    private static final File PROP_FILE = new File("config\\resumes.properties");
    private static final MainConfig INSTANCE = new MainConfig();

    private Properties prop = new Properties();
    private File storageDir;

    public static MainConfig get(){
        return INSTANCE;
    }

    private MainConfig(){
        try (InputStream inputStream = new FileInputStream(PROP_FILE)){
            System.out.println("load properties");
            prop.load(inputStream);
            storageDir = new File(prop.getProperty("storage.dir"));
        } catch ( IOException e){
            throw new IllegalStateException("Invalid config file " + PROP_FILE.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        System.out.println(storageDir.getPath());
        return storageDir;
    }
}
