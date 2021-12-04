package com.tdd.crud.test;

import com.github.pagehelper.PageInfo;
import com.tdd.crud.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianbuer
 * @date 2021/11/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springMVC.xml"})
public class MvcTest {
    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;
    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
@Test
    public void testPage() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pageNo", "2")).andReturn();
        PageInfo pageInfo = (PageInfo) result.getRequest().getAttribute("pageInfo");
        System.out.println(pageInfo.getPages()+"：：共几页");
        System.out.println(pageInfo.getPageNum()+"::当前页码");
        System.out.println(pageInfo.getTotal()+"::记录数");
        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        for (int navigatepageNum : navigatepageNums) {
            System.out.print(" ,"+navigatepageNum);
        }
        ArrayList<Employee> emps = (ArrayList<Employee>) pageInfo.getList();
        for (Employee emp : emps) {
            System.out.println(emp);
        }

    }
}
