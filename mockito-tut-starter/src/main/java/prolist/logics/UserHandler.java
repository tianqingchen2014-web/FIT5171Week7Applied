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

        User user = userDAO.load(personId);

        // ⭐ 关键修复点：不仅判断 null，还要判断 id
        if (user == null || user.getId() == null) {
            throw new SQLException("User not found");
        }

        if (password == null) {
            return false;
        }

        if (password.equals(user.getPassword())) {
            return false;
        }

        return false;
    }
}
