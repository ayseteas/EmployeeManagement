package com.em;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        employeeService.createTable();

        Scanner scan = new Scanner(System.in);
        int select;


        do{
            System.out.println("-----------------------------------");
            System.out.println(" Employee Management System ");
            System.out.println("1. Register an Employee");
            System.out.println("2. List All Employee");
            System.out.println("3. Update an Employee");
            System.out.println("4. Delete an Employee By ID");
            System.out.println("0. Exit");
            System.out.println("Select An Activity");
            System.out.println("-----------------------------------");

             select = scan.nextInt();
             scan.nextLine();

             switch (select)
             {
                 case 1:
                     //Register an Employee
                     employeeService.saveEmployee();
                     break;
                 case 2:
                     //List All Employee
                     employeeService.findAllEmployees();
                     break;
                 case 3:
                     //Update an Employee
                     System.out.println("Enter Employee ID");
                     int id = scan.nextInt();
                     employeeService.updateEmployee(id);
                     break;
                 case 4:
                     //Delete an Employee by ID
                     break;
                 case 0:
                     //Exit
                     System.out.println("Thank you for using the App. Have a good day!");
                     break;
                 default:
                     System.out.println("Invalid Input. Try again");
                     break;
             }

        }while(select != 0);

    }


}
