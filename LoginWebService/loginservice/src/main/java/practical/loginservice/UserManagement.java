package practical.loginservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserManagement {
    
    private Map<Long, User> users = Database.getRegisteredUsers();

    public UserManagement()
    {
        users.put(1l, new User(1l, "stefan", "feit123"));
    }

    public List<User> getAllUsers()
    {
        return new ArrayList<User>(users.values());
    }

    public User getUser(long id)
    {
        return users.get(id);
    }

    public User registerUser(String username, String password)
    {
        User user = new User(1l, username, password);
        boolean check = checkRegistered(user.getUsername());
        if(!(check))
        {
            user.setId(users.size() + 1);
            users.put(user.getId(), user);
            return user;
        }
        return null;
    }

    public boolean unregisterUser(String username, String password, String confirmpassword)
    {
        if(password.equals(confirmpassword))
        {
            List<User> registered = new ArrayList<User>(users.values());
            for(User user : registered)
            {
                if(user.getUsername().equals(username) && user.getPassword().equals(password))
                {
                    users.remove(user.getId());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkRegistered(String username)
    {
        List<User> registered = new ArrayList<User>(users.values());
        for(User user : registered)
        {
            if(user.getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkLoginCredentials(String username, String password)
    {
        List<User> registered = new ArrayList<User>(users.values());
        for(User user : registered)
        {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }

}