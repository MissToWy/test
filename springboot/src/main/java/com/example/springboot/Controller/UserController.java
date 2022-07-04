package com.example.springboot.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.Service.UserService;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(("/user"))
public class UserController {
    @Resource
    private UserService userService;
    //注册
    @PostMapping("/Register")
    public Result Register(@RequestBody User user){
        System.out.println(user.getUsername()+"-----"+user.getPassword());
        if (user.getUsername()==null || user.getPassword()==null){
            return Result.error("-1","用户名或密码不为空");
        }
        Result result = userService.Register(user);
        return result;
    }
    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if (user.getUsername()==null || user.getPassword()==null){
            return Result.error("-1","用户名或密码不为空");
        }
        Result result = userService.selectOne(user);
        return result;
    }
    //根据id查询一条信息
    @PostMapping("/sele")
    public Result sele(@RequestBody int id){
        Result sele = userService.sele(id);
        return sele;
    };
    //插入信息
    @PostMapping
    public Result insertAll(@RequestBody User user){
        Result result = userService.insertAll(user);
        return result;
    }
    //修改信息
    @PutMapping
    public Result update(@RequestBody User user){
        int update = userService.update(user);
        return Result.success();
    }
    //分页
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") int pageNum ,
                              @RequestParam(defaultValue = "10") int pageSize ,
                              @RequestParam(defaultValue = "") String search ){
        Page<User> userPage = userService.selectPage(pageNum, pageSize, search);
        return Result.success(userPage);
    }
    //删除信息
    @DeleteMapping
    public Result delete(@RequestParam int id){
        int update = userService.delete(id);
        return Result.success();
    }
}
