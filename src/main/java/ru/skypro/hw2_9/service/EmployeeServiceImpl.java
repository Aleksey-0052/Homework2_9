package ru.skypro.hw2_9.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.hw2_9.exceptions.EmployeeAlreadyAddedException;
import ru.skypro.hw2_9.exceptions.EmployeeNotFoundException;
import ru.skypro.hw2_9.exceptions.EmployeeStorageIsFullException;
import ru.skypro.hw2_9.exceptions.InvalidInputException;
import ru.skypro.hw2_9.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    private static final Integer MAX_EMPLOYEES = 10;

    //public EmployeeServiceImpl(Map<String, Employee> employees) {     // В таком виде создает конструктор

        //this.employees = employees;
    //}

    public EmployeeServiceImpl() {

        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer departmentId) {
        validateInput(firstName, lastName);

        //if (!validateInput(firstName, lastName)) {  // Второй вариант применения метода валидации
            //throw new InvalidInputException();
        //}                                           // Вынесли в отдельный метод валидации

        Employee employee = new Employee(firstName, lastName, salary, departmentId);

        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник с такими именем и фамилией уже существует");
        } else if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }

        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        //if (!validateInput(firstName, lastName)) {   // Второй вариант применения метода валидации
            //throw new InvalidInputException();
        //}                                            // Вынесли в отдельный метод валидации

        Employee employee = new Employee(firstName, lastName);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник с такими именем и фамилией не найден");
        }

        employees.remove(employee.getFullName());
        return employees.get(employee.getFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        //if (!validateInput(firstName, lastName)) {  // Второй вариант применения метода валидации
            //throw new InvalidInputException();
        //}                                           // Вынесли в отдельный метод валидации

        Employee employee = new Employee(firstName, lastName);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник с такими именем и фамилией не найден");
        }

        return employees.get(employee.getFullName());
    }

    @Override
    public Collection<Employee> findAll() {

        return employees.values();
    }

    private void validateInput(String firstName, String lastName) {
        // if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName)));  // импортируем класс StringUtils
        if (!(isAlpha(firstName) && isAlpha(lastName))) {    // импортируем статический метод из класса StringUtils
            throw new InvalidInputException("Имя или фамилия сотрудника содержат недопустимые символы");
        }
    }

    // Второй вариант применения метода валидации
    //private boolean validateInput2(String firstName, String lastName) {
        //return isAlpha(firstName) && isAlpha(lastName);  // Данный метод выдает true, если в строке содержатся только буквы
    //}

}
