package ru.netology;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        try {
            application.run();
        } catch (InterruptedException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}