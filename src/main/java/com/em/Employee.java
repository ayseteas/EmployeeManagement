package com.em;

public class Employee {

    //Create an employees table with id, first_name, last_name, email, salary, and hire_date.

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private double salary;
    private String hire_date;

    //Constructor (We need full parameter and no parameter constructors)

    public Employee(String first_name, String last_name, String email, double salary, String hire_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.salary = salary;
        this.hire_date = hire_date;
    }

    public Employee() {
    }

    // Getter - Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    //toString()

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", hire_date='" + hire_date + '\'' +
                '}';
    }
}
