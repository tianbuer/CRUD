package com.tdd.crud.service.impl;

import com.tdd.crud.bean.Employee;
import com.tdd.crud.bean.EmployeeExample;
import com.tdd.crud.dao.EmployeeMapper;
import com.tdd.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianbuer
 * @date 2021/11/24
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);

        return employees;
    }

    @Override
    public List<Employee> exists(String empName) {
        EmployeeExample employeeExample= new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        List<Employee> employees = employeeMapper.selectByExampleWithDept(employeeExample);
        return employees;
    }

    @Override
    public Employee getEmpById(Integer id) {
        return employeeMapper.selectByPrimaryKeyWithDept(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteEmployeeBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.insert(employee);
    }


}
