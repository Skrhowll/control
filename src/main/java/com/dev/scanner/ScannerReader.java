package com.dev.scanner;

import java.util.Scanner;

public class ScannerReader {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their name
        System.out.print("Enter your name: ");

        // Read the input provided by the user
        String name = scanner.nextLine();

        // Display a greeting message using the input provided by the user
        System.out.println("Hello, " + name + "!");

        // Close the Scanner object to release resources
        scanner.close();
    }
}
