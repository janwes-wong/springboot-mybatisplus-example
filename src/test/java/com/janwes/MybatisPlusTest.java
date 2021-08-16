package com.janwes;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.janwes.mapper.UserMapper;
import com.janwes.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes
 * @date 2021/8/16 16:05
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增用户
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUserName("alan");
        user.setPassword("qazwsx741");
        user.setName("alan");
        user.setAge(25);
        user.setEmail("test7@xxx.com");
        int i = userMapper.insert(user);
        System.out.println("影响行数:" + i);
    }

    /**
     * 根据id删除
     */
    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(7L);
        System.out.println("影响行数:" + i);
    }

    /**
     * 根据id批量删除
     */
    @Test
    public void deleteBatchIds() {
        List<Long> list = new ArrayList<>();
        list.add(2L);
        list.add(4L);
        list.add(5L);
        int i = userMapper.deleteBatchIds(list);
        System.out.println("影响行数:" + i);
    }

    /**
     * 根据 columnMap(表字段Map对象) 条件，删除记录
     */
    @Test
    public void deleteByMap() {
        Map<String, Object> map = new HashMap();
        map.put("user_name", "zhangsan");
        map.put("password", "123456");
        int i = userMapper.deleteByMap(map);
        System.out.println("影响行数:" + i);
    }

    /**
     * 根据id修改用户
     */
    @Test
    public void updateById() {
        User user = new User();
        user.setId(5L);
        user.setPassword("159753");
        int i = userMapper.updateById(user);
        System.out.println("影响行数:" + i);
    }

    /**
     * 不带分页条件查询
     */
    @Test
    public void selectPage() {
        // 当前页码
        int currentPage = 1;
        // 每页显示条数
        int size = 2;
        IPage<User> page = new Page<>(currentPage, size);

        IPage<User> userPage = userMapper.selectPage(page, null);
        // 当前页的分页数据
        List<User> userList = userPage.getRecords();
        System.out.println("当前页的分页数据：" + userList);
        // 总页数
        long totalPages = userPage.getPages();
        System.out.println("总页数：" + totalPages);
        // 总记录数
        long totalCount = userPage.getTotal();
        System.out.println("总记录数：" + totalCount);
    }

    /**********************条件构造查询***********************/
    /**
     * 基础查询
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE user_name = ? AND age <= ? AND name IN (?,?)
     */
    @Test
    public void testWrapper() {
        // 创建查询条件构建器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置条件
        queryWrapper.eq("user_name", "lisi")
                .le("age", 10)
                .in("name", "李四", "王五");
        List<User> userList = userMapper.selectList(queryWrapper);

        System.out.println(userList);
    }

    /**
     * 逻辑查询
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE user_name = ? OR age > ? OR name IN (?,?)
     */
    @Test
    public void testWrapperOr() {
        // 创建查询条件构建器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置条件
        queryWrapper.eq("user_name", "huge")
                .or()
                .gt("age", 32)
                .or()
                .in("name", "李四", "王五");
        List<User> userList = userMapper.selectList(queryWrapper);

        System.out.println(userList);
    }

    /**
     * 模糊查询
     * 左模糊likeLeft(%?)  SELECT id,user_name,password,name,age,email FROM tb_user WHERE user_name LIKE %h
     * 右模糊likeRight(?%) SELECT id,user_name,password,name,age,email FROM tb_user WHERE user_name LIKE 胡%
     * like(%?%) SELECT id,user_name,password,name,age,email FROM tb_user WHERE name LIKE %胡%
     */
    @Test
    public void testWrapperLike() {
        // 创建查询条件构建器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置条件
        //queryWrapper.likeLeft("user_name", "h");
        //queryWrapper.likeRight("name","胡");
        queryWrapper.like("name", "胡");
        List<User> userList = userMapper.selectList(queryWrapper);

        System.out.println(userList);
    }

    /**
     * 排序查询
     * orderByDesc 降序
     * orderByAsc 升序
     */
    @Test
    public void testWrapperOrderBy() {
        // 创建查询条件构建器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置条件
        //queryWrapper.orderBy(true,true,"id");// 升序
        //queryWrapper.orderBy(true,false,"id");// 降序
        //queryWrapper.orderByDesc("age");
        queryWrapper.orderByAsc("age");
        List<User> userList = userMapper.selectList(queryWrapper);

        System.out.println(userList);
    }

    /**
     * select指定需要查询的字段
     */
    @Test
    public void testWrapperSelect() {
        // 创建查询条件构建器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置条件
        queryWrapper.orderByAsc("age").select("id", "name");
        List<User> userList = userMapper.selectList(queryWrapper);

        System.out.println(userList);
    }

    /**
     * 分页条件查询
     */
    @Test
    public void selectPageByWrapper() {
        // 当前页码
        int currentPage = 1;
        // 每页显示条数
        int size = 2;
        // 创建分页对象
        IPage<User> page = new Page<>(currentPage, size);
        // 创建查询条件构建器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("age", 32);
        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        // 当前页的分页数据
        List<User> userList = userIPage.getRecords();
        System.out.println("当前页的分页数据：" + userList);
        // 总页数
        long totalPages = userIPage.getPages();
        System.out.println("总页数：" + totalPages);
        // 总记录数
        long totalCount = userIPage.getTotal();
        System.out.println("总记录数：" + totalCount);
    }

    @Test
    public void selectUsers() {
        // 当前页码
        int currentPage = 1;
        // 每页显示条数
        int size = 2;
        // 关键字
        int keyword = 25;
        // 创建分页对象
        IPage<User> page = new Page<>(currentPage, size);
        // 自定义实现的分页查询
        IPage<User> userIPage = userMapper.selectUsers(page, keyword);
        // 当前页的分页数据
        List<User> userList = userIPage.getRecords();
        System.out.println("当前页的分页数据：" + userList);
        // 总页数
        long totalPages = userIPage.getPages();
        System.out.println("总页数：" + totalPages);
        // 总记录数
        long totalCount = userIPage.getTotal();
        System.out.println("总记录数：" + totalCount);
    }

    /****************************LambdaQueryWrapper*******************************/
    /**
     * 条件查询
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE user_name = ?
     */
    @Test
    public void LambdaQueryWrapperSelect() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, "huge");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 条件删除
     * DELETE FROM tb_user WHERE name = ?
     */
    @Test
    public void LambdaQueryWrapperDelete() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, "西门吹雪");
        int i = userMapper.delete(queryWrapper);
        System.out.println("影响行数:" + i);
    }

    /**
     * 方式一
     * 条件更新修改
     * UPDATE tb_user SET password=? WHERE user_name = ?
     */
    @Test
    public void LambdaQueryWrapperUpdate01() {
        LambdaUpdateWrapper<User> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(User::getUserName, "hls")
                .set(User::getPassword, "147369");
        int i = userMapper.update(null, queryWrapper);
        System.out.println("影响行数:" + i);
    }

    /**
     * 方式二
     * 条件更新修改
     * UPDATE tb_user SET password=?, age=? WHERE user_name = ?
     */
    @Test
    public void LambdaQueryWrapperUpdate02() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_name", "laopeng");
        User user = new User();
        user.setPassword("lalalappedc");
        user.setAge(32);
        int i = userMapper.update(user, wrapper);
        System.out.println("影响行数:" + i);
    }
}
