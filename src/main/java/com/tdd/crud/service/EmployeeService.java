package com.tdd.crud.service;


import com.tdd.crud.bean.Employee;
import com.tdd.crud.bean.EmployeeExample;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    List<Employee> exists(String empName);
    Employee getEmpById(Integer id);
    void updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
    void deleteEmployeeBatch(List<Integer> ids);
    void addEmployee(Employee employee);
}
