package com.tdd.crud.service.impl;

import com.tdd.crud.bean.Department;
import com.tdd.crud.dao.DepartmentMapper;
import com.tdd.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianbuer
 * @date 2021/12/1
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
