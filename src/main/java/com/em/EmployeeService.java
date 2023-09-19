package com.em;

import java.util.Scanner;

public class EmployeeService {

    EmployeeRepository employeeRepository = new EmployeeRepository();
    Scanner scan = new Scanner(System.in);


    public void createTable(){
        employeeRepository.createTable();
    }

    public void saveEmployee(){
        System.out.println("Enter first name: ");
        String first_name = scan.nextLine();
        System.out.println("Enter last name: ");
        String last_name = scan.nextLine();
        System.out.println("Enter email address: ");
        String email = scan.nextLine();
        System.out.println("Enter salary: ");
        double salary = scan.nextDouble();
        scan.nextLine();
        System.out.println("Enter hire date: ");
        String hire_date = scan.nextLine();

        Employee employee = new Employee(first_name, last_name, email, salary, hire_date);

        employeeRepository.addEmployee(employee);
    }


}
