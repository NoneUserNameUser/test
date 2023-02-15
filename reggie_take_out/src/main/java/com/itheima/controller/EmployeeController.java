package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.BaseContext;
import com.itheima.common.R;
import com.itheima.entity.Employee;
import com.itheima.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author：陈伟捷
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登陆
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    //通过 HttpServletRequest 对象获取客户端信息以及请求的相关信息
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1.将页面提交的密码进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //3.如果没有查询到返回失败结果
        if (emp == null){
            return R.error("不存在该用户");
        }

        //4.比对密码,不一致返回失败结果
        if (!(emp.getPassword().equals(password))){
            return R.error("密码错误");
        }

        //5.查看员工状态，如果已经禁用，返回员工已禁用
        if (emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //6.登陆成功,将员工id存入Session并返回登陆成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理Session中保存的员工id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    //rest风格
    @PostMapping
    public R<String> save(@RequestBody Employee employee,HttpServletRequest request){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //Long empId = (Long) request.getSession().getAttribute("employee");
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);
        log.info("新增员工: {}",employee);
        employeeService.save(employee);
        return R.success("添加员工成功");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //构造分页构造器
        Page<Employee> pageInfo = new Page(page,pageSize);

        //构造查询构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加查询条件
        queryWrapper.like(StringUtils.hasLength(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行分页查询
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 修改
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee){
//        //获取当前登陆用户id
//        long empId = (long) httpServletRequest.getSession().getAttribute("employee");
//        BaseContext.setCurrentId(empId);
//
//        //设置修改时间和用户
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);

        //执行修改语句
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    /**
     * 根据id获取员工信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    // category?id=1623735920545144834,直接获取id   employee/1623714109795233794,此时用@PathVariable
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询信息");
        Employee emp = employeeService.getById(id);
        return emp != null? R.success(emp):R.error("没有查询到对应员工信息");
    }


}
