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

        User user;

        // ⭐ 关键点1：DAO 抛异常 → 你也要抛
        try {
            user = userDAO.load(personId);
        } catch (SQLException e) {
            throw new SQLException("User not found");
        }

        // ⭐ 关键点2：user == null 也要抛
        if (user == null) {
            throw new SQLException("User not found");
        }

        // ⭐ 关键点3：null password
        if (password == null) {
            return false;
        }

        // ⭐ 关键点4：相同 password
        if (password.equals(user.getPassword())) {
            return false;
        }

        // Phase 1 到这里结束
        return false;
    }
}
