package com.syc.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syc.common.QueryPageParam;
import com.syc.common.Result;
import com.syc.entity.User;
import com.syc.service.IUserService;
import freemarker.template.utility.StringUtil;
import org.apache.catalina.valves.rewrite.RewriteCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author syc
 * @since 2023-10-26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no) {
        List list = userService.lambdaQuery().eq(User::getNo, no).list();
        return list.size() > 0 ? Result.suc(list) : Result.fail();
    }

    //增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.suc() : Result.fail();
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.updateById(user) ? Result.suc() : Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return userService.removeById(id) ? Result.suc() : Result.fail();
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return userService.removeById(id);
    }

    //查(模糊，匹配)
    @PostMapping("listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
//        lambdaQueryWrapper.like(User::getName, user.getName());//模糊匹配
//        lambdaQueryWrapper.eq(User::getName, user.getName());//精确匹配
        if (!StringUtils.isEmpty(user.getName()))
            lambdaQueryWrapper.like(User::getName, user.getName());
        return Result.suc(userService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName, (String) param.get("name"));//精确匹配

        IPage result = userService.page(page, lambdaQueryWrapper);
//        System.out.println("total = " + result.getTotal());

        return result.getRecords();


    }

    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName, (String) param.get("name"));//精确匹配

//        IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page, lambdaQueryWrapper);
//        System.out.println("total = " + result.getTotal());

        return result.getRecords();


    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List list = userService.lambdaQuery()
                .eq(User::getNo, user.getNo())
                .eq(User::getPassword, user.getPassword()).list();
        return list.size() > 0 ? Result.suc(list.get(0)) : Result.fail();
    }

    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        Page<User> page = new Page();
        String name = (String) param.get("name");
        String sex = (String) param.get("sex");
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (!StringUtils.isEmpty(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(User::getName, name);//精确匹配
        }
        if (!StringUtils.isEmpty(sex)) {
            lambdaQueryWrapper.eq(User::getSex, sex);//精确匹配
        }

//        IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page, lambdaQueryWrapper);
//        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }
}
