package prolist.logics;

import prolist.dataaccess.UserDAO;
import prolist.model.User;

import java.sql.SQLException;

/**
 * @author Yuan-Fang Li
 * @author yqtian for version 2.0
 * @version $Id: $
 */
public class UserHandler {
    private final UserDAO userDAO;

    public UserHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean updatePassword(Long personId, String password) throws SQLException {
        // TODO: Load the user via userDAO, validate the request, and update the password.
        throw new UnsupportedOperationException("TODO: implement updatePassword");
    }

}
