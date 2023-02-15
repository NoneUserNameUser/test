package com.itheima.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *通过使用 MybatisPlusInterceptor，可以方便的实现以下功能：
 *  1.自动填充：自动填充创建人、创建时间、更新人、更新时间等字段。
 *  2.动态表名：动态生成表名，以适应多租户或分表策略。
 *  3.逻辑删除：支持逻辑删除，只更新删除标识，而不是真正删除记录。
 *  4.分页：支持简单分页、高级分页、自动分页。
 * @version 1.0
 * @Author：陈伟捷
 */
@Configuration
public class MybatisPlusConfig {


    //分页功能的查询总数和单页显示数都靠这个
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){


        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
