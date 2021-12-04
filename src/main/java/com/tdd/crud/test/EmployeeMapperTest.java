package com.tdd.crud.test;

import com.tdd.crud.bean.Department;
import com.tdd.crud.bean.Employee;
import com.tdd.crud.dao.DepartmentMapper;
import com.tdd.crud.dao.EmployeeMapper;
import com.tdd.crud.service.EmployeeService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author tianbuer
 * @date 2021/11/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmployeeMapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD() throws Exception {
        /*departmentMapper.insertSelective(new Department(null,"人事部"));
        departmentMapper.insertSelective(new Department(null,"安保部"));
        System.out.println(departmentMapper);*/

        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
    @Test
    public void test(){
        System.out.println(employeeService);
        List<Employee> employees = employeeService.getAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
    @Test
    public void add(){
        Employee employee = new Employee(null,"aaaaaa","asasda@123.com", (byte) 1,2);
        System.out.println(employee);
        employeeService.addEmployee(employee);
    }
    @Test
    public void testUpdate(){
        employeeService.updateEmployee(new Employee(2,null,"tianbuer@123.com", (byte) 1,2));
    }
}
