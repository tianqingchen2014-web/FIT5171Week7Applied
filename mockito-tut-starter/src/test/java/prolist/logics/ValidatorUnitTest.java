package prolist.logics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import prolist.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by yli on 23/03/2016.
 * Updated by yqtian for version 2.0.
 */
@Tag("extra")
public class ValidatorUnitTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
    }

    @Test
    public void validUserEmailReturnsTrue() {
        User user = new User();
        String email = "abc@example.com";
        assertTrue(validator.validate(user, "email", email));
        assertEquals(email, user.getEmail());
    }

    @Test
    public void validUserRoleReturnsTrue() {
        User user = new User();
        User.Role role = User.Role.ACADEMIC;
        assertTrue(validator.validate(user, "role", role.toString()));
        assertEquals(role, user.getRole());
    }

    @Test
    public void nullUserEmailThrowsAnException() {
        User user = new User();
        assertThrows(ValidationException.class, () -> validator.validate(user, "email", null));
    }
}
