package com.app.remicall.services;

import com.app.remicall.domain.Role;
import com.app.remicall.domain.User;
import com.app.remicall.repositories.UserRepository;
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

import java.util.Collections;

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
    public void activateUserTest(){
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
    public void activateUserFailTest(){
        boolean isUserActivated = userService.activateUser("true-activate");

        Assert.assertFalse(isUserActivated);
        verify(userRepository, times(0))
                .save(ArgumentMatchers.any(User.class));
    }
}
