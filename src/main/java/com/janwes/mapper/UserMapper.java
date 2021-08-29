package com.janwes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.janwes.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mapper
 * @date 2021/8/16 15:55
 * @description
 */
//@Mapper // 在启动类加上@MapperScan("com.janwes.mapper")注解，此处就无需加入@Mapper注解
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectUsers(IPage<User> page, @Param("age") int age);

    List<User> search(@Param("ids") List<Integer> ids, @Param("age") int age);
}
