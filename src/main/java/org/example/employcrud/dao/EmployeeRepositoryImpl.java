package org.example.employcrud.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.employcrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private EntityManager theEntityManager;

    @Autowired
    public EmployeeRepositoryImpl (EntityManager tempEntityManager) {
        theEntityManager = tempEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        String jpql = "From Employee";
        TypedQuery<Employee> employeeTypedQuery = theEntityManager.createQuery(jpql, Employee.class);

        return employeeTypedQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        String jpql = "From Employee Where id=:employId";
        TypedQuery<Employee> employeeTypedQuery = theEntityManager.createQuery(jpql, Employee.class);
        employeeTypedQuery.setParameter("employId", id);

        List<Employee> employees = employeeTypedQuery.getResultList();
        Employee theEmployee = null;
        if (employees.isEmpty()) {
            theEmployee = employees.get(0);
        }
        return theEmployee;
    }

    @Override
    @Transactional
    public Employee save(Employee theEmployee) {
        theEntityManager.merge(theEmployee);
        return theEmployee;
    }

    @Override
    public void remove(int id) {

    }
}
