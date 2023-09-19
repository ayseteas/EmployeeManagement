package com.em;

import java.sql.*;

public class EmployeeRepository {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String url = "jdbc:postgresql://localhost:5432/employee_management";
    private String username = "postgres";
    private String password = "1222";

    //Step 1: Create a Connection
    public void createConnection(){

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }

    }

    public void createPreparedStatement(String query){
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
    }


    //Create a table in the database
    public void createTable(){
        createConnection();
        createStatement();
        String query = "CREATE TABLE IF NOT EXISTS tbl_employee" +
                "(id SERIAL PRIMARY KEY," +
                "first_name VARCHAR(50)," +
                "last_name VARCHAR(50)," +
                "email VARCHAR(50)," +
                "salary NUMERIC," +
                "hire_date VARCHAR(50))";


        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public void addEmployee(Employee employee){
        String query =  "INSERT INTO tbl_employee (first_name,last_name,email,salary,hire_date) values(?,?,?,?,?);";
       // String query =  "INSERT INTO tbl_employee values(?,?,?,?,?);";
        createConnection();
        createPreparedStatement(query);

        try {
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getHire_date());

            preparedStatement.executeUpdate();
            System.out.println("Employee Registered");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }


    }





}
