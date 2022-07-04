package com.example.springboot.Controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.Mapper.BookMapper;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.entity.Book;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {
    @Resource
    private com.example.springboot.Service.BookService BookService;
    //插入信息
    @PostMapping
    public Result insertAll(@RequestBody Book book){
        System.out.println(book);
        Result result = BookService.insertAll(book);
        return result;
    }
    //修改信息
    @PutMapping
    public Result update(@RequestBody Book book){
        int update = BookService.update(book);
        return Result.success();
    }
    //删除信息
    @DeleteMapping
    public Result delete(@RequestParam int bh){
        int update = BookService.delete(bh);
        return Result.success();
    }
    //分页
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") int pageNum ,
                              @RequestParam(defaultValue = "10") int pageSize ,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "1") int bh
                              ){
        Page<Book> BookPage = BookService.selectPage(pageNum, pageSize, search,bh);
        return Result.success(BookPage);
    }
    @GetMapping("/fx")
    public Result<?> fx(){
        Result<?> fx = BookService.fx();
        return fx;
    }
}
