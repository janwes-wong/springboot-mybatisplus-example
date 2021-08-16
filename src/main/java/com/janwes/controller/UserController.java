package com.janwes.controller;

import com.janwes.pojo.User;
import com.janwes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
