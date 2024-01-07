package org.example.employcrud.dao;

import org.example.employcrud.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee theEmployee);
    void remove(int id);
}
