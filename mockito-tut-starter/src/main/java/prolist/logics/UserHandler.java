package prolist.logics;

import prolist.dataaccess.UserDAO;
import prolist.model.User;

import java.sql.SQLException;

public class UserHandler {

    private final UserDAO userDAO;

    public UserHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean updatePassword(Long personId, String password) throws SQLException {

        // ⭐ 1. 先查 user（关键顺序）
        User user = userDAO.load(personId);

        // ⭐ 2. user 不存在 → 必须 throw（不管 password 是啥）
        if (user == null) {
            throw new SQLException("User not found");
        }

        // ⭐ 3. 再判断 password
        if (password == null) {
            return false;
        }

        // ⭐ 4. 相同 password
        if (password.equals(user.getPassword())) {
            return false;
        }

        return false;
    }
}
