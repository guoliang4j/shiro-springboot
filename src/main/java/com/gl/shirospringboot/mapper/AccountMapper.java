package com.gl.shirospringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gl.shirospringboot.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guoliang
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
