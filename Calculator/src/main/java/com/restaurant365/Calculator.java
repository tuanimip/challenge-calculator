/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurant365;

import com.restaurant365.util.CalculatorException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tuan.phanvan
 */
public class Calculator {

    public static void main(String[] args) throws IOException {
        System.out.print("-------Calculator console application-------\n");
        System.out.print("Note: type \"exit\" to exit application\n\n");

        InputStream stream = System.in;
        Scanner scanner = new Scanner(stream);
        String input;

        while (true) {
            System.out.println("-------\nInput: ");
            if (scanner.hasNext()) {
                input = scanner.next();
                if (!input.equalsIgnoreCase("exit")) {
                    try {
                        int result = processCalculator(input);
                        System.out.println("Output: " + result);
                    } catch (CalculatorException calExc) {
                        System.out.println(calExc.getMessage());
                    }

                } else {
                    break;
                }
            }
            scanner.nextLine();
        }

        scanner.close();
    }

    public static int processCalculator(String input) throws CalculatorException {
        String[] params = input.split(",", -1);

        int result = 0;
        List<Integer> numbers = new ArrayList<>();
        int size = 0;
        if (params.length > 0) {
            for (String e : params) {
                if (!e.isEmpty()) {
                    try {
                        int number = Integer.parseInt(e);
                        size++;
                        if (size > 2) {
                            throw new CalculatorException("Error occurred: maximum of numbers must be 2.");
                        }
                        numbers.add(number);
                    } catch (NumberFormatException ex) {
                    }
                }
            }

            if (!numbers.isEmpty()) {
                for (int number : numbers) {
                    result += number;
                }
            }
        }

        return result;
    }
}
