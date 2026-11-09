
package murach.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import murach.business.User;

public class UserDB {

    private static final List<User> users =
            Collections.synchronizedList(new ArrayList<>());

    public static void insert(User user) {
        users.add(user);
    }
}
