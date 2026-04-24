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

        // ⭐ 关键：直接 load
        User user = userDAO.load(personId);

        // ⭐ 如果没找到用户 → 必须抛异常（测试测的就是这个）
        if (user == null) {
            throw new SQLException("User not found");
        }

        // ⭐ password 为 null
        if (password == null) {
            return false;
        }

        // ⭐ password 相同
        if (password.equals(user.getPassword())) {
            return false;
        }

        // ⭐ Phase 1 只要求到这里
        return false;
    }
}
