package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.R;
import com.itheima.dto.DishDto;
import com.itheima.dto.SetmealDto;
import com.itheima.entity.Category;
import com.itheima.entity.Employee;
import com.itheima.entity.Setmeal;
import com.itheima.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Author：陈伟捷
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    SetmealService setmealService;
    @GetMapping("/page")
    public R<Page<SetmealDto>> page(Long page,Long pageSize,String name){
        Page<SetmealDto> pageInfo = setmealService.pageWithCategory(page, pageSize, name);
        return R.success(pageInfo);
    }
    @GetMapping("{id}")
    public R<SetmealDto> getByIdWithCategoryId(@PathVariable Long id){
        SetmealDto setmealDto = setmealService.getByIdWithCategoryId(id);
        return R.success(setmealDto);
    }
    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto){
        //执行修改语句
        setmealService.updateById(setmealDto);
        return R.success("员工信息修改成功");
    }
}
