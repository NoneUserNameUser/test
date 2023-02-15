package com.itheima.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.dto.DishDto;
import com.itheima.dto.SetmealDto;
import com.itheima.entity.Dish;
import com.itheima.entity.Setmeal;

/**
 * @version 1.0
 * @Author：陈伟捷
 */

public interface SetmealService extends IService<Setmeal> {
    public Page<SetmealDto> pageWithCategory(Long page, Long pageSize, String name);

    public SetmealDto getByIdWithCategoryId(Long CategoryId);
}
