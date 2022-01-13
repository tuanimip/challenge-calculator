/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.restaurant365;

import com.restaurant365.util.CalculatorException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    public void calculatorTest() {
        //first requirement
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
        } catch (CalculatorException calExc) {
            //Throw an exception when more than 2 numbers are provided
            assertEquals("Error occurred: maximum of numbers must be 2.", calExc.getMessage());
        }
    }
}