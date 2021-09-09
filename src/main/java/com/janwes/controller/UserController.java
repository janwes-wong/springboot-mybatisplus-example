package com.janwes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.janwes.mapper.UserMapper;
import com.janwes.pojo.User;
import com.janwes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.controller
 * @date 2021/8/16 15:54
 * @description 控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    /**
     * 根据id更新数据
     *
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setPassword("wsx123");
        boolean result = userService.updateById(user);
        if (result) {
            return "更新成功";
        }
        return "更新失败";
    }

    /**
     * 保存数据
     *
     * @return
     */
    @GetMapping("/save")
    public String save() {
        User user = new User();
        user.setUserName("andy");
        user.setPassword("qazwsx741");
        user.setName("andy");
        user.setAge(25);
        user.setEmail("test9@xxx.com");
        boolean result = userService.save(user);
        if (result) {
            return "保存成功";
        }
        return "保存失败";
    }

    @GetMapping("/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF8");
        try {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            User user = userMapper.selectOne(queryWrapper.eq(User::getUserName, username));
            if (Objects.isNull(user)) {
                response.getWriter().write("{\"code\":\"5000\",\"message\":\"账号不存在\",\"data\":\"\"}");
                return;
            }

            if (!username.equals(user.getUserName()) || !password.equals(user.getPassword())) {
                response.getWriter().write("{\"code\":\"5000\",\"message\":\"账号或密码错误\",\"data\":\"\"}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
