/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package services;

import dto.UserDto;
import entities.User;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Arek
 */
@Transactional
public class UserServiceIT {
    
    public UserServiceIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class UserService.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User user = null;
        UserService instance = null;
        instance.addUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class UserService.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Integer id = null;
        UserService instance = null;
        User expResult = null;
        User result = instance.getUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUsers method, of class UserService.
     */
    @Test
    public void testFindUsers() {
        System.out.println("findUsers");
        String name = "";
        UserService instance = null;
        Iterable<User> expResult = null;
        Iterable<User> result = instance.findUsers(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class UserService.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String name = "";
        String pass = "";
        UserService instance = null;
        UserDto expResult = null;
        UserDto result = instance.registerUser(name, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class UserService.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String name = "";
        String inputPass = "";
        UserService instance = null;
        User expResult = null;
        User result = instance.loginUser(name, inputPass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomPass method, of class UserService.
     */
    @Test
    public void testRandomPass() {
        System.out.println("randomPass");
        int length = 0;
        UserService instance = null;
        String expResult = "";
        String result = instance.randomPass(length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of facebookLogin method, of class UserService.
     */
    @Test
    public void testFacebookLogin() {
        System.out.println("facebookLogin");
        String token = "";
        UserService instance = null;
        User expResult = null;
        User result = instance.facebookLogin(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of googleLogin method, of class UserService.
     */
    @Test
    public void testGoogleLogin() throws Exception {
        System.out.println("googleLogin");
        String token = "";
        UserService instance = null;
        User expResult = null;
        User result = instance.googleLogin(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of promoteMod method, of class UserService.
     */
    @Test
    public void testPromoteMod() {
        System.out.println("promoteMod");
        int userId = 0;
        int targetId = 0;
        String auth = "";
        UserService instance = null;
        instance.promoteMod(userId, targetId, auth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of demoteMod method, of class UserService.
     */
    @Test
    public void testDemoteMod() {
        System.out.println("demoteMod");
        int userId = 0;
        int targetId = 0;
        String auth = "";
        UserService instance = null;
        instance.demoteMod(userId, targetId, auth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePass method, of class UserService.
     */
    @Test
    public void testChangePass() {
        System.out.println("changePass");
        Integer userId = null;
        String newPass = "";
        String oldPass = "";
        String auth = "";
        UserService instance = null;
        instance.changePass(userId, newPass, oldPass, auth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of banUser method, of class UserService.
     */
    @Test
    public void testBanUser() {
        System.out.println("banUser");
        int userId = 0;
        int targetId = 0;
        String auth = "";
        UserService instance = null;
        instance.banUser(userId, targetId, auth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unbanUser method, of class UserService.
     */
    @Test
    public void testUnbanUser() {
        System.out.println("unbanUser");
        int userId = 0;
        int targetId = 0;
        String auth = "";
        UserService instance = null;
        instance.unbanUser(userId, targetId, auth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
