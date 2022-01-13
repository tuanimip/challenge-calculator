/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.restaurant365;

import com.restaurant365.util.CalculatorException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tuan.phanvan
 */
public class CalculatorJUnitTest {

    public CalculatorJUnitTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    //first requirement
    @Test
    public void firstTest() {
        try {
            //20 will return 20
            assertEquals(20, Calculator.processCalculator("20"));

            //1,5000 will return 5001
            assertEquals(5001, Calculator.processCalculator("1,5000"));

            //4,-3 will return 1
            assertEquals(1, Calculator.processCalculator("4,-3"));

            //empty input or missing numbers should be converted to 0
            assertEquals(0, Calculator.processCalculator(""));
            assertEquals(0, Calculator.processCalculator("a,b"));

            //invalid numbers should be converted to 0 e.g. 5,tytyt will return 5
            assertEquals(5, Calculator.processCalculator("5,tytyt"));

            //Support a maximum of 2 numbers
            Calculator.processCalculator("1,2,3");
            fail("Not throw CalculatorException as expected");
        } catch (CalculatorException calExc) {
            //Throw an exception when more than 2 numbers are provided
            assertEquals("Error occurred: maximum of numbers must be 2.", calExc.getMessage());
        }
    }

    //second requirement
    @Test
    public void secondTest() {
        try {
            //1,2,3,4,5,6,7,8,9,10,11,12 will return 78
            assertEquals(78, Calculator.processCalculator("1,2,3,4,5,6,7,8,9,10,11,12"));
        } catch (CalculatorException calExc) {
        }
    }

    //third requirement
    @Test
    public void thirdTest() {
        try {
            //1\n2,3 will return 6
            assertEquals(6, Calculator.processCalculator("1\n2,3"));
        } catch (CalculatorException calExc) {
        }
    }

    //fourth requirement
    @Test
    public void fourthTest() {
        try {
            //Deny negative numbers by throwing an exception that includes all of the negative numbers provided
            Calculator.processCalculator("1,-2,3,5,-9");
            fail("Not throw CalculatorException as expected");
        } catch (CalculatorException calExc) {
            assertEquals("[-2, -9] Error occurred: negative numbers are not accepted.", calExc.getMessage());
        }
    }

    //fifth requirement
    @Test
    public void fifthTest() {
        try {
            //Make any value greater than 1000 an invalid number e.g. 2,1001,6 will return 8
            assertEquals(8, Calculator.processCalculator("2,1001,6"));
        } catch (CalculatorException calExc) {
        }
    }

    //sixth requirement
    @Test
    public void sixthTest() {
        try {
            //#\n2#5 will return 7
            assertEquals(7, Calculator.processCalculator("//#\n2#5"));
            //#\n2#5 will return 7
            assertEquals(102, Calculator.processCalculator("//,\n2,ff,100"));
            //#\n2#5 will return 7
            assertEquals(103, Calculator.processCalculator("//$\n2$ff$101"));
        } catch (CalculatorException calExc) {
        }
    }
}
