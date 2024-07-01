/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package services;

import mainPack.ForumApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@SpringBootTest(classes=ForumApplication.class)
public class CheckUserIdIT {
    
    CheckUserId instance;
    @Autowired
    UserRepository userRepository;
    
    public CheckUserIdIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        instance = new CheckUserId(userRepository);
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
        String auth = "Basic dGVzdDE6dGVzdA==";
        int id = 10;
        boolean expResult = true;
        boolean result = instance.checkId(auth, id);
        assertEquals(expResult, result);
    }
    
}
