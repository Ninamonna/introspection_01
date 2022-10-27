package org.example.introspection.service;


import org.example.introspection.domain.Role;
import org.example.introspection.domain.User;
import org.example.introspection.repo.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertFalse;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest{

   @Autowired
   private UserService userService;

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private SmtpMailSender smtpMailSender;
    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    public void testAddUser() {
        User user = new User();
        user.setEmail("some@mail.ru");
        boolean addUser = userService.addUser(user);

        Assert.assertTrue(addUser);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(smtpMailSender, Mockito.times(1)) // не проходит тест
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    @Test
    public void addUserFailTest() {
        User user = new User();
        user.setUsername("John");

        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("John");

        boolean addUser = userService.addUser(user);

        Assert.assertFalse(addUser);

        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        Mockito.verify(smtpMailSender, Mockito.times(0))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );

    }

    @Test
    public void activateUser() {
        User user = new User();

        user.setActivationCode("bingo!");

        Mockito.doReturn(user)
                .when(userRepo)
                .findByActivationCode("activate");

        boolean isUserActivated = userService.activateUser("activate");

        Assert.assertTrue(isUserActivated);
        Assert.assertNull(user.getActivationCode());

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    public void activateUserFailTest() {

        boolean isUserActivated = userService.activateUser("activate me");

        Assert.assertFalse(isUserActivated);
        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }


}