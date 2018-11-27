package com.laiyy.mybatisboot;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.laiyy.mybatisboot.model.User;
import com.laiyy.mybatisboot.model.enums.AgeEnum;
import com.laiyy.mybatisboot.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MybatisBootApplicationTests {

//    @Autowired
//    private IUserService userService;

    @Test
    public void testCondition() {
        Condition.create()
                // 使用 SQL 函数
                .setSqlSelect("sum(balance)")
                // 为空
                .isNull("name")
                .eq("name", "zhangsan")
                // AND 连接后续条件
                .and("id=1")
                // 使用OR换行，并添加一个带()的新的条件
                .orNew("user_nick", "Lily")
                // 添加OR条件
                .or("status=1")
                // NOT LIKE条件语句，value中无需前后%
                .notLike("user_nickname", "John")
                // 使用AND连接并换行，并添加一个带 () 的新的条件
                .andNew("pwd=11")
                // is not null 条件，多个字段以逗号分隔。
                .isNotNull("n1, n2")
                // is null 条件，多个字段以逗号分隔。
                .isNull("n3")
                // SQL中groupBy关键字跟的条件语句，QL 中的 Group by 语句，无需输入 Group By 关键字
                .groupBy("x1").groupBy("x2,x3")
                // SQL中having关键字跟的条件语句，having关键字后面跟随的语句
                .having("x1=11").having("x3=433")
                // 排序
                .orderBy("dd")
                // 如果 condition 为 false， 则不会拼接 order by
                .orderBy(false, "aa")
                // 是否正序排序
                .orderBy("ss", false)
                // 相等
                .eq("username", "lyl")
                // in 字句
                .in("balance", new Integer[]{1, 2, 3, 4, 5})
                // between 语句
                .between("age", 40, 45)
                // 小于
                .lt("age", 44)
                // 大于
                .gt("age", 42)
                // 小于等于
                .le("balance", 88)
                // 大于等于
                .ge("balance", 66);

    }

    @Test
    public void contextLoads() {

        // 创建一个条件查询构造器
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        // SQL中WHERE关键字跟的条件语句
        wrapper.where("name={0}", "三毛")
                .andNew()
                .like("name", "zhangsan").or()
                .like("nickName", "lisi").or()
                .like("phone", "123")
                .orderBy("id", false)
                .orderBy("seq");
                // AND 连接后续条件
//                .and("id=1")
//                // 使用OR换行，并添加一个带()的新的条件
//                .orNew("user_nick", "Lily")
//                // 添加OR条件
//                .or("status=1")
//                // NOT LIKE条件语句，value中无需前后%
//                .notLike("user_nickname", "John")
//                // 使用AND连接并换行，并添加一个带 () 的新的条件
//                .andNew("pwd=11")
//                // is not null 条件，多个字段以逗号分隔。
//                .isNotNull("n1, n2")
//                // is null 条件，多个字段以逗号分隔。
//                .isNull("n3")
//                // SQL中groupBy关键字跟的条件语句，QL 中的 Group by 语句，无需输入 Group By 关键字
//                .groupBy("x1").groupBy("x2,x3")
//                // SQL中having关键字跟的条件语句，having关键字后面跟随的语句
//                .having("x1=11").having("x3=433")
//                // 排序
//                .orderBy("dd")
//                // 如果 condition 为 false， 则不会拼接 order by
//                .orderBy(false, "aa")
//                // 是否正序排序
//                .orderBy("ss", false)
//                // 相等
//                .eq("username", "lyl")
//                // in 语句
//                .in("channelId", Arrays.asList(1, 2, 3, 4))
//                // between 语句
//                .between("age", 40, 45)
//                // 小于
//                .lt("age", 44)
//                // 大于
//                .gt("age", 42)
//                // 小于等于
//                .le("balance", 88)
//                // 大于等于
//                .ge("balance", 66);
        System.out.println(wrapper.getSqlSegment());


    }

}
