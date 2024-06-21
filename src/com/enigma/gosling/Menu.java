package com.enigma.gosling;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("1. Add Task");
        System.out.println("2. View Task");
        System.out.println("3. Edit Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");

        System.out.println("masukan pilihan : ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Add Task");
                break;
            case "2":
                System.out.println("View Task");
                break;
            case "3":
                System.out.println("Edit Task");
                break;
            case "4":
                System.out.println("Delete Task");
                break;
            case "5":
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void addTask() {

    }

    public void viewTask() {

    }

    public void editTask() {

    }

    public void deleteTask() {

    }
}
