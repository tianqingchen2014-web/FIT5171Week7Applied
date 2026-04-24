package prolist.logics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prolist.dataaccess.UserDAO;
import prolist.model.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Yuan-Fang Li
 * @author yqtian for version 2.0
 * @version $Id: $
 */
public class UserHandlerUnitTest {
    private UserHandler userHandler;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        userDAO = mock(UserDAO.class);
        userHandler = new UserHandler(userDAO);
    }

    @Test
    public void noUserFoundThrowsSQLException() throws SQLException {
        // TODO: Use Mockito to make userDAO.load(...) return null, then assert that
        // updatePassword(...) throws SQLException with a helpful message.
        long id = 0L;
        when(userDAO.load(id)).thenReturn(null);
        SQLException exception = assertThrows(SQLException.class,
                () -> userHandler.updatePassword(id, "password"));
        assertTrue(exception.getMessage().contains("No user found"));
    }

    @Test
    public void nullPasswordReturnsFalse() throws SQLException {
        // TODO: Stub a User from userDAO.load(...), call updatePassword(..., null),
        // and assert that the update fails.
        long id = 0L;
        when(userDAO.load(id)).thenReturn(new User());
        boolean successful = userHandler.updatePassword(id, null);
        assertFalse(successful, "Null password");
    }

    @Test
    public void samePasswordReturnsFalse() throws SQLException {
        // TODO: Stub a User whose current password matches the new password, then
        // assert that updatePassword(...) returns false.
        long id = 0L;
        User user = new User();
        String password = "abcd";
        user.setPassword(password);
        when(userDAO.load(id)).thenReturn(user);
        boolean successful = userHandler.updatePassword(id, password);
        assertFalse(successful, "Same password");
    }

    /*
    @Test
    public void differentPasswordReturnsTrue() throws SQLException {
        // Phase 2 task:
        // 1. Uncomment this test.
        // 2. Stub a User whose current password differs from the new password.
        // 3. Assert that updatePassword(...) returns true.
        // 4. Verify that userDAO.update(user) is called.
        long id = 0L;
        User user = new User();
        user.setPassword("old-password");
        when(userDAO.load(id)).thenReturn(user);

        boolean successful = userHandler.updatePassword(id, "new-password");

        assertTrue(successful, "Different password");
        assertTrue("new-password".equals(user.getPassword()));
        verify(userDAO).update(user);
    }
    */

}
