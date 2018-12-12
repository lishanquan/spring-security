package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsq on 2018/11/20.
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors){

        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error ->{
//                    FieldError fieldError = (FieldError)error;
//                    String message = fieldError.getField() + " " + fieldError.getDefaultMessage();

                    System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");

        return user;
    }

    /**
     * 创建用户
     * @param user @RequestBody把接收到的Json字符串转换成User对象
     *
     * @Valid 注解和User对象里的@NotBlank注解配套使用
     * BindingResult ： 如果传入参数不满足校验条件，相应的错误信息会被放入BindingResult对象中
     * @return
     */
    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors){

        if (errors.hasErrors()){
//            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
            List<ObjectError> errorList = errors.getAllErrors();
            for (ObjectError objectError : errorList){
                System.out.println(objectError.getDefaultMessage());
            }
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());//注意，在前后台之间只传递时间戳格式的时间，最终展示成什么格式交给前台处理

        user.setId("1");

        return user;
    }

    /**
     *
     * @param condition 自定义参数对象
     * @param pageable spring-data中Pageable对象
     * @return
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 2,size = 17,sort = "username,asc") Pageable pageable){

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> userList = new ArrayList<User>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());

        return userList;
    }

    /**
     * 在URL中使用正则表达式：限制传入id参数只能是数字
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id){
        User user = new User();
        user.setUsername("daniel");
        return user;
    }

}
