package com.laiyy.mybatisboot;

import com.laiyy.mybatisboot.controller.UserController;
import com.laiyy.mybatisboot.service.IUserService;
import com.laiyy.mybatisboot.service.impl.UserServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author laiyy
 * @date 2018/12/6 16:59
 * @description
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Test
    public void test() throws Exception {
//        BDDMockito.given(this.userService.selectListBySQL()).willReturn(Lists.newArrayList());
        mockMvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
