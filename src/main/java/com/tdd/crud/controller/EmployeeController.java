package com.tdd.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdd.crud.bean.Employee;
import com.tdd.crud.bean.Mag;
import com.tdd.crud.dao.EmployeeMapper;
import com.tdd.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianbuer
 * @date 2021/11/26
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeMapper employeeMapper;

    //@RequestMapping("/emps")
   /* public String getEmps(Model model, @RequestParam(value ="pageNo",defaultValue = "1")Integer pageNo,@RequestParam(value = "pageSize",defaultValue="10")Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Employee> all = employeeService.getAll();
        PageInfo<Employee> pageInfo = new PageInfo<>(all,5);
        model.addAttribute("pageInfo",pageInfo);

        return "list";
    }*/

    @RequestMapping("/emps")
    public Mag getEmpsWithJson(@RequestParam(value ="pageNo",defaultValue = "1")Integer pageNo, @RequestParam(value = "pageSize",defaultValue="10")Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Employee> all = employeeService.getAll();
        PageInfo<Employee> pageInfo = new PageInfo<>(all,5);
        return Mag.success().add("pageInfo", pageInfo);
    }

    /*JSR303校验*/
    @PostMapping("/emp")
    public Mag addEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
           Map<String,String> map = new HashMap<>();
           List<FieldError> fieldErrors = result.getFieldErrors();
           for (FieldError fieldError : fieldErrors) {
               map.put(fieldError.getField(), fieldError.getDefaultMessage());
           }
           return Mag.fail().add("errorFields", map);
       }else {
           employeeService.addEmployee(employee);
           return Mag.success();
       }

    }
    @PostMapping("/checkuser")
    public Mag checkuser(@RequestParam("empName")String empName){
        List<Employee> exists = employeeService.exists(empName);
        if(exists.isEmpty()){
            return Mag.success();
        }else {
            return Mag.fail().add("va_msg", "用户名已存在");
        }
    }

    @GetMapping("/emp/{id}")
    public Mag getEmp(@PathVariable("id")Integer id) {
        Employee emp = employeeService.getEmpById(id);
        return Mag.success().add("emp", emp);
    }

    @PutMapping(value="/emp/{empId}")
    public Mag updateEmp(@Valid Employee employee,BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Mag.fail().add("errorFields", map);
        }else {
            employeeService.updateEmployee(employee);
            return Mag.success();
        }
    }
    @DeleteMapping("/emp/{ids}")
    public Mag deleteEmployee(@PathVariable("ids") String ids) {
        if (ids.contains("-")){
            String[] strings = ids.split("-");
            ArrayList<Integer> intIds = new ArrayList<>();
            for (String str : strings) {
                intIds.add(Integer.valueOf(str));
            }
            employeeService.deleteEmployeeBatch(intIds);
            return Mag.success().add("msg", "删除成功！");

        }else {
            employeeService.deleteEmployee(Integer.parseInt(ids));
            return Mag.success().add("msg", "删除成功！");
        }
    }


}
