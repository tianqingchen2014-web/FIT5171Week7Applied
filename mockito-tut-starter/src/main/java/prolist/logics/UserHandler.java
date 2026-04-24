package prolist.logics;

import prolist.dataaccess.UserDAO;
import prolist.model.User;

import java.sql.SQLException;

/**
 * @author Yuan-Fang Li
 * @author yqtian
 */
public class UserHandler {

    private final UserDAO userDAO;

    public UserHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean updatePassword(Long personId, String password) throws SQLException {

        // 1. 获取用户
        User user = userDAO.findById(personId);

        // 2. 用户不存在 → 抛异常（必须有 message）
        if (user == null) {
            throw new SQLException("User not found for id: " + personId);
        }

        // 3. password 为 null → false
        if (password == null) {
            return false;
        }

        // 4. 新旧密码一样 → false
        if (password.equals(user.getPassword())) {
            return false;
        }

        // Phase 1 到这里结束（不要写 update！）
        return false;
    }
}
