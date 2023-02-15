package com.itheima.common;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @version 1.0
 * @Author：陈伟捷
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal();

    /**
     * 保存id
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
