package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertwaters on 7/11/17.
 */

public class UserManager {
    private Map<String, User> _userMap;
    private User _currentUser;

    public UserManager() {
        _userMap = new HashMap<String, User>();
        makeSomeUsers();
    }

    private void makeSomeUsers() {
        _userMap.put("Null user", new User("Null", "No", "Name", "passw0rd", "null@gatech.edu", "0000000000", User.accountType.get(1)));

        _userMap.put("Other null user", new User("OtherNull", "No", "Name", "passw0rd", "null@gatech.edu", "0000000000", User.accountType.get(1)));

    }

    public boolean tryLogin(String uid, String pass) {
        User user = _userMap.get(uid);
        if (user == null) return false;
        if (user.getPassword().equals(pass)) {
            _currentUser = user;
            return true;
        }
        return false;
    }

    public boolean sessionStarted() {
        return _currentUser != null;
    }

}
