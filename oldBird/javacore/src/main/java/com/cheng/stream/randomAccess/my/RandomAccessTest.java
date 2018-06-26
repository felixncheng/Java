package com.cheng.stream.randomAccess.my;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;


/**
 * @author cheng_mboy
 */
public class RandomAccessTest {

    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
            for (Employee employee : staff) {
                writeData(employee, out);
            }
        }

        try (RandomAccessFile in = new RandomAccessFile("employee.dat", "r")) {
            int n = (int) in.length() / Employee.RECORD_SIZE;
            Employee[] newstaff = new Employee[n];

            for (int i = n - 1; i >= 0; i--) {
                in.seek(i * Employee.RECORD_SIZE);
                newstaff[i] = readData(in);
            }

            for (Employee e : newstaff) {
                System.out.println(e);
            }
        }


    }

    private static void writeData(Employee e, DataOutput out) throws IOException {
        DataIO.writeFixString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());

        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    private static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int year = in.readInt();
        int month = in.readInt();
        int day = in.readInt();
        return new Employee(name, salary, year, month, day);
    }
}
