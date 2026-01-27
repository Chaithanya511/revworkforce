package revworkforce.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import revworkforce.Model.Employee;
import revworkforce.service.AuthService;

public class AuthServiceTest {
    AuthService authService = new AuthService();

    @Test
    void testValidLogin() {
        Employee emp = authService.login("chaitu", "chaitu123");
        assertNotNull(emp);
        assertEquals("chaitu", emp.getEmpId());
    }

    @Test
    void testInvalidLogin() {
        Employee emp = authService.login("wrong", "wrong");
        assertNull(emp);
    }
}

