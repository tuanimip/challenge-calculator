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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tuan.phanvan
 */
public class Calculator {

    public static void main(String[] args) throws IOException {
        printMessage("-------Calculator console application-------\n");
        printMessage("Note: press \"enter\" to input new parameter\n"
                + "type \"run\" to process calculator\n"
                + "type \"exit\" to exit application\n\n");

        InputStream stream = System.in;
        Scanner scanner = new Scanner(stream);
        String inputAll = "";
        String inputDilimiter = "";
        boolean isInputDelimiter = true;
        List<String> delimiters = new ArrayList<>();

        printFirstMessage();
        while (true) {
            if (scanner.hasNext()) {
                String input = scanner.next();
                if (input.isEmpty() && isInputDelimiter) {
                    printMessage("please input value: ");
                } else {
                    if (!input.equalsIgnoreCase("exit")) {
                        if (input.equalsIgnoreCase("run")) {
                            try {
                                if (!delimiters.isEmpty()) {
                                    if (delimiters.size() == 1) {
                                        inputDilimiter = delimiters.get(0);
                                    }
                                    inputAll = "//" + inputDilimiter + "\n" + inputAll;

                                    if (!inputAll.isEmpty()) {
                                        inputAll = inputAll.substring(0, inputAll.length() - 1);
                                    }
                                    int result = processCalculator(inputAll);
                                    printMessage("Output: " + result);
                                }
                            } catch (CalculatorException calExc) {
                                printMessage(calExc.getMessage());
                            }
                            printFirstMessage();
                            inputAll = "";
                            isInputDelimiter = true;
                            inputDilimiter = "";
                            delimiters.clear();
                        } else if (input.equalsIgnoreCase("end")) {
                            isInputDelimiter = false;
                            printMessage("Input numbers: ");
                        } else if (isInputDelimiter) {
                            delimiters.add(input);
                            inputDilimiter = inputDilimiter + "[" + input + "]";
                        } else {
                            inputAll = inputAll + input + "\n";
                        }
                    } else {
                        break;
                    }
                }
            }
            scanner.nextLine();
        }

        scanner.close();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printFirstMessage() {
        printMessage("-------\nPlease input the characters as your delimiter (support mutil delimiter,\n"
                + "press \"Enter\" to input new delimiter). Input \"end\" to end input delimiter:");
    }

    public static int processCalculator(String input) throws CalculatorException {
        int result = 0;
        List<Integer> numbers = new ArrayList<>();
        List<Integer> nagativeNumbers = new ArrayList<>();
        String[] params = new String[1];
        String regex;
        if (input.startsWith("//")) {
            int indexNewLine = input.indexOf("\n");

            if (indexNewLine >= 3) {
                String dilimiter = input.substring(2, indexNewLine);
                String numberString = input.substring(indexNewLine + 1, input.length());

                if (!numberString.isEmpty()) {
                    if (dilimiter.contains("[")) {
                        //mutil dimiliter
                        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                        Matcher matcher = pattern.matcher(dilimiter);
                        String delimiterStr = "";
                        while (matcher.find()) {
                            delimiterStr = delimiterStr + matcher.group(1);
                        }
                        regex = "[" + delimiterStr + "\n]+";
                    } else {
                        //single dimiliter
                        regex = "[" + dilimiter + "\n]+";
                    }

                    params = numberString.split(regex);
                }
            }
        } else {
            regex = "[,\n]+";
            params = input.split(regex);
        }

        if (params.length > 0) {
            for (String e : params) {
                if (!e.isEmpty()) {
                    try {
                        int number = Integer.parseInt(e);
                        if (number < 0) {
                            nagativeNumbers.add(number);
                        } else if (number <= 1000) {
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
//    }

        return result;
    }
}
