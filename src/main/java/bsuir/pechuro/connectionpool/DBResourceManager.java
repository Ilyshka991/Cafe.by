package bsuir.pechuro.connectionpool;

import java.util.ResourceBundle;

/**
 * class DBResourceManager created
 */
public final class DBResourceManager {
    private static final DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("dao/db");

    private DBResourceManager() {
    }

    /**
     * @return DBResourceManager
     */
    public static DBResourceManager getInstance() {
        return instance;
    }

    /**
     * @param key
     * @return String
     */
    public String getValue(String key) {
        return bundle.getString(key);
    }
}
