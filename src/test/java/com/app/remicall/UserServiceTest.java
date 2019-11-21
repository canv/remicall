package com.app.remicall;

import com.app.remicall.domain.Role;
import com.app.remicall.domain.User;
import com.app.remicall.repositories.UserRepository;
import com.app.remicall.services.MailSenderService;
import com.app.remicall.services.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailSenderService mailSenderService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUserTest() {
        User testUser = new User();
        testUser.setEmail("ex@mp.le");

        boolean isUserCreated = userService.addUser(testUser);

        Assert.assertTrue(isUserCreated);
        Assert.assertNotNull(testUser.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(testUser.getRoles()).matches(Collections.singleton(Role.USER)));
        verify(userRepository, times(1)).save(testUser);
        verify(mailSenderService, times(1)).sendActivationMessage(testUser);
    }

    @Test
    public void addUserFailTest() {
        User testUser = new User();
        testUser.setUsername("Tester1234567890");
        doReturn(new User())
                .when(userRepository).findByUsername("Tester1234567890");

        boolean isUserCreated = userService.addUser(testUser);

        Assert.assertFalse(isUserCreated);
        verify(userRepository, times(0))
                .save(ArgumentMatchers.any(User.class));
        verify(mailSenderService, times(0))
                .sendActivationMessage(testUser);
    }

    @Test
    public void activateUserTest() {
        User testUser = new User();
        testUser.setActivationCode("pre-activate");
        doReturn(testUser)
                .when(userRepository).findByActivationCode("true-activate");

        boolean isUserActivated = userService.activateUser("true-activate");

        Assert.assertTrue(isUserActivated);
        Assert.assertNull(testUser.getActivationCode());
        verify(userRepository, times(1))
                .save(testUser);
    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = userService.activateUser("true-activate");

        Assert.assertFalse(isUserActivated);
        verify(userRepository, times(0))
                .save(ArgumentMatchers.any(User.class));
    }

    @Test
    public void updateUserRolesTest() {
        User testUser = new User();
        String testName = "testName";
        testUser.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        HashSet<Role> testRoles = new HashSet<>();
        testRoles.add(Role.USER);
        testRoles.add(Role.ADMIN);
        Map<String, String> roles = new HashMap<>();
        roles.put("USER", "-");
        roles.put("ADMIN", "-");

        userService.updateUserRoles(testUser, testName, roles);

        Assert.assertEquals(testName, testUser.getUsername());
        Assert.assertEquals(testRoles, testUser.getRoles());
        verify(userRepository, times(1))
                .save(ArgumentMatchers.any(User.class));
    }

    @Test
    public void updateUserProfileSuccessTest() {
        User testUser = new User();
        testUser.setEmail("current@e.mail");
        testUser.setPassword("currentPass");
        String testEmail = "test@e.mail";
        String testPassword = "testPass";

        userService.updateUserProfile(testUser,testPassword,testEmail);

        Assert.assertNotNull(testUser.getActivationCode());
        Assert.assertEquals(passwordEncoder.encode(testPassword), testUser.getPassword());
        verify(userRepository, times(1))
                .save(ArgumentMatchers.any(User.class));
        verify(mailSenderService, times(1))
                .sendActivationMessage(ArgumentMatchers.any(User.class));
    }
}
