package com.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//springbootjunit
@RunWith(SpringRunner.class)// spring容器
@SpringBootTest //读取
public class DemoApplicationTests{

    @Autowired
    private UserMapper userMapper;

    /*
    查询所有
     */
    @Test
    public void  testfind(){
        List<User> users = this.userMapper.selectList(null);
        for(User u:users){
            System.out.println(u);
        }
    }
    /*
    插入数据
     */
    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(22);
        user.setMail("adfad@qq.com");
        user.setName("tom1");
        user.setUserName("tom2");
        user.setPassword("111");
        user.setAddress("wuhan");

        int i = this.userMapper.insert(user);
        System.out.println(user.getId());
    }

    /*
    修改
     */
    @Test
    public void testUpdate(){

        User user = new User();
        user.setId(6L);
        user.setName("mike");
        int i = this.userMapper.updateById(user);
        //update tb_user set name='mike' where id=6
        System.out.println(i);
    }

    @Test
    public void testUpdate2(){
        User user = new User();
        user.setName("jetty");
        //where
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",3L);
        //update tb_user set name='jetty' where id=3
        int i = this.userMapper.update(user, queryWrapper);
        System.out.println(i);

    }

    //更新
    @Test
    public void testUpdate3(){
        UpdateWrapper<User> objectUpdateWrapper = new UpdateWrapper<>();
        objectUpdateWrapper.eq("id",2L).set("user_name","tom");

        int i = this.userMapper.update(null, objectUpdateWrapper);
        System.out.println(i);

    }
    //删除
    @Test
    public void testDelete(){

        int i = this.userMapper.deleteById(1L);
        System.out.println(i);





    }


    //删除
    @Test
    public void testDelete2(){

        Map<String,Object> o= new HashMap<>();
        o.put("name","李四");
        o.put("age",20);
        //delete from tb_user where name='tt' and age=11
        int i = this.userMapper.deleteByMap(o);
        System.out.println(i);
    }

    @Test
    public void testDelete3(){
        User user = new User();
        user.setName("jetty");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        int delete = this.userMapper.delete(queryWrapper);
        System.out.println(delete);

    }


    @Test
    public void testDelete4(){
        //delete from tb_user where id in (4l,7l)
        this.userMapper.deleteBatchIds(Arrays.asList(4L,7L));
    }

    @Test
    public void testSelect1(){

        User user = this.userMapper.selectById(5L);
        System.out.println(user);

    }
    @Test
    public void testSelect2(){
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(5L, 6L));
        for(User u:users){

            System.out.println(u);
        }


    }

    @Test
    public void testSelect3(){

        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("name","mike");
        User user = this.userMapper.selectOne(objectQueryWrapper);
        System.out.println(user);
    }

    @Test
    public void testSelect4(){

        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.ge("age",10);//>=
        Integer i = this.userMapper.selectCount(objectQueryWrapper);
        //
        System.out.println(i);
    }

    @Test
    public void selectById(){
        User user = this.userMapper.selectById(2L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds(){
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","李四");
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void pageSelect(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",18);
        Page<User> userPage = new Page<>(1,2);
        IPage<User> iPage = this.userMapper.selectPage(userPage, wrapper);
        System.out.println(iPage.getPages());
        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","孙七");
        map.put("age","24");
        wrapper.allEq(map);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void UserMapperTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","t");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void UserMapperTest1(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
}