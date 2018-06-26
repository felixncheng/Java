package com.cheng.stream.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;


/**
 * @author cheng_mboy
 */
public class Stream {

    public static void main(String[] args) throws IOException {

        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8")) {
            writeData(staff, out);
        }

        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), "UTF-8")) {
            Employee[] newStaff = readData(in);
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }


    }

    private static Employee[] readData(Scanner in) {
        int length = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[length];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    @SuppressWarnings("Duplicates")
    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate date = LocalDate.parse(tokens[2]);
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }

    private static void writeData(Employee[] staff, PrintWriter out) {
        out.println(staff.length);
        for (Employee e : staff) {
            writeEmployee(out, e);
        }
    }

    private static void writeEmployee(PrintWriter out, Employee employee) {
        out.println(employee.getName() + "|" + employee.getSalary() + "|" + employee.getHireDay());
    }

    private static void testPrintWriter() {
        try (PrintWriter out = new PrintWriter("employee.txt")) {
            String name = "Harrdy Hacker";
            double salry = 75000;
            out.print(name);
            out.print(' ');
            out.println(salry);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
