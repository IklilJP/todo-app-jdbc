package com.enigma.gosling;

import com.enigma.gosling.db.Jdbc;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() throws SQLException {
        System.out.println("-".repeat(50));
        System.out.println("Selamat Datang di menu ToDo App");
        System.out.println("-".repeat(50));
        System.out.println("1. Add Task");
        System.out.println("2. View Task");
        System.out.println("3. Edit Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.println("-".repeat(50));
        System.out.println("masukan pilihan : ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("-".repeat(50));
                System.out.println("Tambah Task");
                System.out.println("-".repeat(50));
                addTask();
                displayMenu();
            case "2":
                System.out.println("-".repeat(50));
                System.out.println("Tampilkan Task");
                System.out.println("-".repeat(50));
                viewTask();
                displayMenu();
            case "3":
                System.out.println("-".repeat(50));
                System.out.println("Edit Task");
                System.out.println("-".repeat(50));
                editTask();
                displayMenu();
            case "4":
                System.out.println("-".repeat(50));
                System.out.println("Delete Task");
                System.out.println("-".repeat(50));
                deleteTask();
                displayMenu();
            case "5":
                System.out.println("Keluar");
                break;
            default:
                System.out.println("Invalid choice");
                displayMenu();
        }
    }

    public void addTask() throws SQLException {
        String todo_name, priority, descripsion, is_active;

        do {
            System.out.println("masukan todo name : ");
            todo_name = scanner.nextLine();
            todo_name = todo_name.trim();
            if (todo_name.isBlank() || todo_name.isEmpty()) {
                System.out.println("Todo Name Tidak boleh kosong ! ");
            }
        } while (todo_name.isBlank() || todo_name.isEmpty());

        do {
            System.out.println("priority (high/medium/low)");
            System.out.println("masukan priority : ");
            priority = scanner.nextLine();
            priority = priority.toLowerCase();
            if (!priority.equals("high") && !priority.equals("medium") && !priority.equals("low")) {
                System.out.println("Priority Tidak Valid");
            }
        } while (!priority.equals("high") && !priority.equals("medium") && !priority.equals("low"));

        System.out.println("masukan descripsion : ");
        descripsion = scanner.nextLine();
        descripsion = descripsion.trim();

        do {
            System.out.println("apakah aktif (y/n) : ");
            is_active = scanner.nextLine();
            if (is_active.equals("y")) {
                is_active = "active";
            } else if (is_active.equals("n")) {
                is_active = "inactive";
            }
            if (!is_active.equals("active") && !is_active.equals("inactive")) {
                System.out.println("Status Tidak Valid");
            }
        } while (!is_active.equals("active") && !is_active.equals("inactive"));

        Jdbc.add(todo_name, priority, descripsion, is_active);
    }

    public void viewTask() throws SQLException {
        Jdbc.showAll();
    }

    public void editTask() throws SQLException {
        String id, todo_name, priority, descripsion, is_active;
        System.out.println("Masukan id yang ingin di edit : ");
        id = scanner.nextLine();
        Boolean check = Jdbc.findbyId(id);
        if (!check) {
            System.out.println("ID Tidak Ditemukan");
            displayMenu();
        } else {

            System.out.println("masukan todo name : ");
            todo_name = scanner.nextLine();
            todo_name = todo_name.trim();

            do {
                System.out.println("priority (high/medium/low)");
                System.out.println("masukan priority : ");
                priority = scanner.nextLine();
                priority = priority.toLowerCase();
                if (!priority.equals("high") && !priority.equals("medium") && !priority.equals("low")) {
                    System.out.println("Priority Tidak Valid");
                }
            } while (!priority.equals("high") && !priority.equals("medium") && !priority.equals("low"));

            System.out.println("masukan descripsion : ");
            descripsion = scanner.nextLine();
            descripsion = descripsion.trim();

            do {
                System.out.println("apakah aktif (y/n) : ");
                is_active = scanner.nextLine();
                if (is_active.equals("y")) {
                    is_active = "active";
                } else if (is_active.equals("n")) {
                    is_active = "inactive";
                }
                if (!is_active.equals("active") && !is_active.equals("inactive")) {
                    System.out.println("Status Tidak Valid");
                }
            } while (!is_active.equals("active") && !is_active.equals("inactive"));

            Jdbc.update(id, todo_name, priority, descripsion, is_active);
        }
    }

    public void deleteTask() throws SQLException {
        String id;
        System.out.println("Enter ID : ");
        id = scanner.nextLine();
        Boolean check = Jdbc.findbyId(id);
        if (!check) {
            System.out.println("ID Tidak Ditemukan");
            displayMenu();
        }
        Jdbc.delete(id);
    }
}
