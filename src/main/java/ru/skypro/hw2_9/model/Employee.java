package ru.skypro.hw2_9.model;

import java.util.Objects;

import static org.springframework.util.StringUtils.capitalize;


public class Employee {

    private String firstName;
    private String lastName;
    private int salary;
    private int departmentId;


    public Employee(String firstName, String lastName) {
        //this.firstName = StringUtils.capitalize(firstName.toLowerCase());  // импортируем класс StringUtils
        //this.lastName = StringUtils.capitalize(lastName.toLowerCase());    // импортируем класс StringUtils
        this.firstName = capitalize(firstName.toLowerCase());                // импортируем статический метод capitalize из класса  StringUtils
        this.lastName = capitalize(lastName.toLowerCase());
        // метод toLowerCase() переводит все буквы строки в строчные (нижний регистр)
        // метод capitalize() переводит первую букву строки в заглавную (верхний регистр)
    }

    public Employee(String firstName, String lastName, Integer salary, Integer departmentId) {
        this.firstName = capitalize(firstName.toLowerCase());                // импортируем статический метод capitalize из класса  StringUtils
        this.lastName = capitalize(lastName.toLowerCase());
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public Integer getSalary() {

        return salary;
    }

    public Integer getDepartmentId() {

        return departmentId;
    }

    public String getFullName () {

        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return departmentId == employee.departmentId && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, departmentId);
    }


}
