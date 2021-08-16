package com.janwes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.janwes.mapper.UserMapper;
import com.janwes.pojo.User;
import com.janwes.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.service.impl
 * @date 2021/8/16 16:02
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
