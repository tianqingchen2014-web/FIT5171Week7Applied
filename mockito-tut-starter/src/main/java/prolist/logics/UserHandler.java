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

        // ⭐ 1. 先判断 password（非常关键）
        if (password == null) {
            return false;
        }

        // ⭐ 2. 再查 user
        User user = userDAO.load(personId);

        // ⭐ 3. 再判断 user 是否存在
        if (user == null) {
            throw new SQLException("User not found");
        }

        // ⭐ 4. password 一样
        if (password.equals(user.getPassword())) {
            return false;
        }

        // Phase 1
        return false;
    }
}
