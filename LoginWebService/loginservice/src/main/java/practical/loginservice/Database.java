package practical.loginservice;

import java.util.HashMap;
import java.util.Map;

public class Database {

    private static Map<Long, User> registeredUsers = new HashMap<>();

    public static Map<Long, User> getRegisteredUsers()
    {
        return registeredUsers;
    }
    
}