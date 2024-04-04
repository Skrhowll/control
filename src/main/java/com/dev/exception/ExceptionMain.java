package com.dev.exception;

public class ExceptionMain {
    public static void main(String[] args) {
        try {
            // bloc of code that can trigger an exception
            String[] names = { "rafa", "roger", "novak" };
            System.out.println("Résultat : " + names[8]);
        } catch (IndexOutOfBoundsException e) {
            // catch bloc to handle exception
            System.err.println("Exception attrapée : " + e.getMessage());
        } finally {
            // Finally bloc to close resources for instance
            System.out.println("Ce bloc est toujours exécuté, qu'une exception soit levée ou non.");
        }
    }
}