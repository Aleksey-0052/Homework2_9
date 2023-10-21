package ru.skypro.hw2_9.service;

import ru.skypro.hw2_9.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, Integer salary, Integer departmentId);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> findAll();

}
