package com.example.springboot.Service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.Mapper.UserMapper;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Result insertAll(User user){
        User user1 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if(user1==null) {
            userMapper.insert(user);
            return Result.success();
        }
        return  Result.error("-2","该用户名已存在，请重新输入");
    }

    public Page<User> selectPage(int pageNum,int pageSize,String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotBlank(search)){//判断search不为空
            wrapper.like(User::getNikeName,search);
        }

        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return userPage;
    }

    public int update(User user) {
        int i = userMapper.updateById(user);
        return i;
    }

    public int delete(int id) {
        int i = userMapper.deleteById(id);
        return i;
    }

    public Result selectOne(User user) {
        User user1 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (user1==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(user1);
    }

    public Result Register(User user) {
        User user1 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if(user1!=null) {
            return Result.error("-2","该用户名已存在，请重新输入");
        }else {
            userMapper.insert(user);
            return Result.success();
        }
    }

    public Result sele(int id) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, id));
        return Result.success(user);
    }
}
