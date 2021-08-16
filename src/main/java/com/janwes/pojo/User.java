package com.janwes.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.pojo
 * @date 2021/8/16 15:52
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@TableName("tb_user") // 指定数据库表名，也可以在application.yml配置文件中配置
public class User implements Serializable {

    // id生成策略：AUTO数据库id自增 也可以在application.yml配置文件中配置
    // @TableId(type = IdType.AUTO)
    private Long id;

    // 数据库字段使用_分割，实体类属性名使用驼峰名称
    @TableField(value = "user_name")
    private String userName;

    private String password;

    private String name;

    private Integer age;

    private String email;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
