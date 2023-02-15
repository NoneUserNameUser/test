package com.itheima.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 在实体类添加注解实现sql操作时自动填充
 * @version 1.0
 * @Author：陈伟捷
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作自动填充
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段填充[insert]" + metaObject.toString());
        metaObject.setValue("createTime",LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        Long empId = BaseContext.getCurrentId();

        metaObject.setValue("createUser",new Long(empId));
        metaObject.setValue("updateUser",new Long(empId));
    }

    /**
     * 修改操作自动填充
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段填充[update]" + metaObject.toString());
        metaObject.setValue("updateTime",LocalDateTime.now());
        Long empId = BaseContext.getCurrentId();
        metaObject.setValue("updateUser",new Long(empId));
    }
}
