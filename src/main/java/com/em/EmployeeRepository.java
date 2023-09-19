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
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Employee findById(int id){
        createConnection();
        Employee employee = new Employee();
        String query = "SELECT * FROM tbl_employee WHERE id=?";
        createPreparedStatement(query);
        try {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name(resultSet.getString("last_name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setHire_date(resultSet.getString("hire_date"));
                employee.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return employee;
    }

    public void listAllEmployees(){
        createConnection();
        createStatement();
        String query = "SELECT * FROM tbl_employee";
        try {
           ResultSet resultSet =  statement.executeQuery(query);
           while (resultSet.next() ){
               System.out.print("ID: " + resultSet.getInt("id")+ " ");
               System.out.print("First Name: " + resultSet.getString("first_name")+ " ");
               System.out.print("Last Name: " + resultSet.getString("last_name")+ " ");
               System.out.print("Email Address: " + resultSet.getString("email")+ " ");
               System.out.print("Salary: " + resultSet.getDouble("salary")+ " ");
               System.out.println("Hire Date: " + resultSet.getString("hire_date")+ " ");
           }
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

    public void update(Employee newEmployee){
        String query = "UPDATE tbl_employee SET first_name=?, last_name=?, email=?, salary=?, hire_date=? WHERE id=?";
        createConnection();
        createPreparedStatement(query);
        try {
            preparedStatement.setString(1, newEmployee.getFirst_name());
            preparedStatement.setString(2, newEmployee.getLast_name());
            preparedStatement.setString(3, newEmployee.getEmail());
            preparedStatement.setDouble(4, newEmployee.getSalary());
            preparedStatement.setString(5, newEmployee.getHire_date());
            preparedStatement.setInt(6, newEmployee.getId());
            int updatedRow = preparedStatement.executeUpdate();
            if(updatedRow>0){
                System.out.println("Employee Updated");
            }else{
                System.out.println("Employee Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void delete(int id){
        createConnection();
        String query = "DELETE FROM tbl_employee WHERE id=?";
        createPreparedStatement(query);
        try {
            preparedStatement.setInt(1, id);
            int deletedRow = preparedStatement.executeUpdate();
            if(deletedRow>0){
                System.out.println("Employee Deleted");
            }else{
                System.out.println("Employee Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
