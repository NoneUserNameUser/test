package com.itheima.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.R;
import com.itheima.dto.DishDto;
import com.itheima.entity.Dish;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Author：陈伟捷
 */
public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public void updateWithFlavor(DishDto dishDto);
    public List<Dish> getByCategoryId(Long categoryId);
    public Page<DishDto> pageWithFlavor(int page, int pageSize, String name);
    public DishDto getByIdWithFlavor(Long id);
    public void status(Integer status,List<Long> ids);
    }
