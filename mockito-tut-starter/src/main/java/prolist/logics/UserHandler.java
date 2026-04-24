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

        // 1. password null → false
        if (password == null) {
            return false;
        }

        User user;

        // 2. DAO 抛异常 → 你也要抛
        try {
            user = userDAO.load(personId);
        } catch (SQLException e) {
            throw new SQLException("User not found");
        }

        // 3. user 为 null → 抛
        if (user == null) {
            throw new SQLException("User not found");
        }

        // 4. password 相同 → false
        if (password.equals(user.getPassword())) {
            return false;
        }

        // Phase 1
        return false;
    }
}
