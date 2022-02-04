package com.gl.shirospringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author guoliang
 */
@Data
public class Account implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
    private String salt;
    @TableField(exist = false)
    private String session;
}
