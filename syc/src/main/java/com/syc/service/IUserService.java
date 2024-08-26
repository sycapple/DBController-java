package com.syc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syc
 * @since 2023-10-26
 */
public interface IUserService extends IService<User> {

    IPage pageC(Page<User> page);

    IPage pageCC(Page<User> page, Wrapper wrapper);
}
