package com.movie.movie.controller;

import cn.hutool.core.util.StrUtil;
import com.movie.movie.controller.util.R;
import com.movie.movie.entity.User;
import com.movie.movie.entity.ViewObject.UserDTO;
import com.movie.movie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 插入一条用户信息--注册
     */
    @PostMapping("/addone")
    public R register(@RequestBody User user){
        System.out.println(user);
        Boolean usernames = userService.getUsernames(user);
        if (usernames == true){
            return new R(false, null, "用户名已存在，请换一个用户名！");
        }else{
            return new R(true, userService.save(user),"注册成功");
        }

    }


    /**
     * 登录+token
     */
    @PostMapping("/login")
    public R login(@RequestBody UserDTO userDTO) throws Exception {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return new R(false,"null","账号或密码不能为空");
        }
//        UserDTO DTO = userService.login(userDTO);
//        System.out.println(DTO);
        return new R(true, userService.login(userDTO),"11");
    }




}
