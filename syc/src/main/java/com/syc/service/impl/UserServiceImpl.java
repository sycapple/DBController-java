package com.syc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syc.entity.User;
import com.syc.mapper.UserMapper;
import com.syc.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author syc
 * @since 2023-10-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public IPage pageC(Page<User> page) {
        return userMapper.pageC(page);
    }

    @Override
    public IPage pageCC(Page<User> page, Wrapper wrapper) {
        return userMapper.pageCC(page, wrapper);
    }
}
