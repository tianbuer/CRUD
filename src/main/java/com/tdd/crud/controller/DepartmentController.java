package com.tdd.crud.controller;

import com.tdd.crud.bean.Department;
import com.tdd.crud.bean.Mag;
import com.tdd.crud.service.DepartmentService;
import com.tdd.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tianbuer
 * @date 2021/12/1
 */
@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping("/depts")
    public Mag getDeptWithJson() {
        List<Department> all = departmentService.getDepts();
        return Mag.success().add("depts", all);

    }
}
