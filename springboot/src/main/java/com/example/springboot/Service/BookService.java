package com.example.springboot.Service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.Mapper.BookMapper;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.entity.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    @Resource
    private BookMapper bookMapper;
    public Result insertAll(Book book) {
        Book book1 = bookMapper.selectOne(Wrappers.<Book>lambdaQuery().eq(Book::getBookname, book.getBookname()));
        if(book1==null) {
            bookMapper.insert(book);
            return Result.success();
        }
        return  Result.error("-2","该图书已收藏，请重新输入");
    }
    public int update(Book book) {
        int i = bookMapper.updateById(book);
        return i;
    }
    public int delete(int bh) {
        int i = bookMapper.deleteById(bh);
        return i;
    }

    public Page<Book> selectPage(int pageNum, int pageSize, String search,int bh) {
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        wrapper.eq(Book::getGuishu,bh);
        if(StrUtil.isNotBlank(search)){//判断search不为空
            wrapper.like(Book::getBookname, search);
        }

        Page<Book> bookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return bookPage;
    }

    public Result<?> fx() {
        List<Book> books = bookMapper.selectList(Wrappers.<Book>lambdaQuery());
        return Result.success(books);
    }
}
