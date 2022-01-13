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
        System.out.print("Note: press \"enter\" to input new parameter\n"
                + "type \"run\" to process calculator\n"
                + "type \"exit\" to exit application\n\n");

        InputStream stream = System.in;
        Scanner scanner = new Scanner(stream);
        String inputAll = "";

        System.out.println("-------\nInput: ");
        while (true) {
            if (scanner.hasNext()) {
                String input = scanner.next();
                if (!input.equalsIgnoreCase("exit")) {
                    if (input.equalsIgnoreCase("run")) {
                        try {
                            if (!inputAll.isEmpty()) {
                                inputAll = inputAll.substring(0, inputAll.length() - 1);
                            }
                            int result = processCalculator(inputAll);
                            System.out.println("Output: " + result);
                        } catch (CalculatorException calExc) {
                            System.out.println(calExc.getMessage());
                        }
                        System.out.println("-------\nInput: ");
                        inputAll = "";
                    } else {
                        inputAll = inputAll + input + "\n";
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
        input = input.replaceAll("\n", ",");
        String[] params = input.split(",", -1);

        int result = 0;
        List<Integer> numbers = new ArrayList<>();
        List<Integer> nagativeNumbers = new ArrayList<>();
        if (params.length > 0) {
            for (String e : params) {
                if (!e.isEmpty()) {
                    try {
                        int number = Integer.parseInt(e);
                        if (number < 0) {
                            nagativeNumbers.add(number);
                        } else {
                            numbers.add(number);
                        }
                    } catch (NumberFormatException ex) {
                    }
                }
            }

            if (!nagativeNumbers.isEmpty()) {
                throw new CalculatorException(nagativeNumbers.toString() + " Error occurred: negative numbers are not accepted.");
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
