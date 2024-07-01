/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package services;

import entities.User;
import mainPack.ForumApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@SpringBootTest(classes=ForumApplication.class)
public class CheckUserIdTest {

    CheckUserId instance;
    UserRepository userRepository;

    public CheckUserIdTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        instance = new CheckUserId(userRepository);
        Mockito.when(userRepository.findFirstByNameEquals("test1")).thenReturn(new User(10,"test1",null,null,null,null,null,null));
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkId method, of class CheckUserId.
     */
    @Test
    public void testCheckId() {
        System.out.println("checkId");
        String auth = "Basic dGVzdDE6dGVzdA==";//Basic dGVzdDE6dGVzdDE=
        int id = 10;
        boolean expResult = true;
        boolean result = instance.checkId(auth, id);
        assertEquals(expResult, result);
    }

}
