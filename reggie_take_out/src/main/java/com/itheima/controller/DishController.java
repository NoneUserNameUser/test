package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.R;
import com.itheima.dto.DishDto;
import com.itheima.entity.Category;
import com.itheima.entity.Dish;
import com.itheima.entity.DishFlavor;
import com.itheima.entity.SetmealDish;
import com.itheima.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Author：陈伟捷
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {


    @Autowired
    private DishService dishService;
    @PostMapping
    public R<String> saveWithFlavor(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        return R.success("保存成功");
    }
    @GetMapping("/page")
    public R<Page<DishDto>> pageWithFlavor(int page, int pageSize, String name) {
        Page<DishDto> dishDtoPage = dishService.pageWithFlavor(page, pageSize, name);
        return R.success(dishDtoPage);
    }
    @GetMapping("/{id}")
    public R<DishDto> getByIdWithFlavor(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }
    @GetMapping("/list")
    public R<List<Dish>> getByCategoryId(Long categoryId){
        List<Dish> dishes = dishService.getByCategoryId(categoryId);
        return R.success(dishes);
    }
    @PutMapping
    public R<String> updateWithFlavor(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return R.success("保存成功");
    }
    @PostMapping("/status/{status}")
    //@RequestParam接收的参数是来自requestHeader中，即请求头
    public R<String> status(@PathVariable Integer status,@RequestParam List<Long> ids){
        dishService.status(status,ids);
        return R.success("修改成功");
    }
    @DeleteMapping
    public R<String> removeByIds(@RequestParam List<Long> ids){
        dishService.removeByIds(ids);
        return R.success("删除成功");
    }
}
