package controller;

import openldap.api.entities.User;
import openldap.api.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes={openldap.api.ldapApiApplication.class})
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        List<User> users = userService.getAll();
        assertNotNull(users);
        assertEquals(users.size(), 2);
    }

    @Test
    public void testFindUserByUid() {
        User user = userService.getUserByUid("alex");
        assertNotNull(user);
        assertEquals(user.getCn(), "Alex");
    }
}