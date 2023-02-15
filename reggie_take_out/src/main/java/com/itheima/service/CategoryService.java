package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.entity.Category;

/**
 * @version 1.0
 * @Author：陈伟捷
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
