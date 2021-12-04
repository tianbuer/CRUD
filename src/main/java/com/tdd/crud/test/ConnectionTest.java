package com.tdd.crud.test;

import com.tdd.crud.bean.Department;
import com.tdd.crud.dao.DepartmentMapper;
import com.tdd.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接测试
 *
 * @author tianbuer
 * @date 2021/11/21
 */
public class ConnectionTest {
    @Test
    public void mbg() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
        if(warnings.isEmpty()){
            System.out.println("aaa");
        }else {
            System.err.println(warnings);
        }
    }
    @Test
    public void testGetEmployeeById(){
        ApplicationContext ioc=
                new ClassPathXmlApplicationContext("applicationContext.xml");
        DepartmentMapper departmentMapper = (DepartmentMapper) ioc.getBean("departmentMapper");
        Department department = new Department(null,"安保部");
        departmentMapper.insertSelective(department);

    }
}
