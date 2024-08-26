package com.syc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syc.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yaml.snakeyaml.scanner.Constant;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author syc
 * @since 2023-10-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage pageC(Page<User> page);

    IPage pageCC(Page<User> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
