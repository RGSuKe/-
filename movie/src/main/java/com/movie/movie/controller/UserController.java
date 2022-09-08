package com.movie.movie.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.movie.movie.controller.util.R;
import com.movie.movie.entity.User;
import com.movie.movie.entity.ViewObject.Cinema_Movie_Schedule;
import com.movie.movie.entity.ViewObject.UserDTO;
import com.movie.movie.service.IUserService;
import com.movie.movie.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
     * 发送邮箱
     */
    @GetMapping("/getEmailCode/{email}")
    public R getEmailCode(@PathVariable String email){
        System.out.println(email);
        Boolean username = userService.getUsernameEmail(email);
        if (username){
            return new R(false, null, "改邮箱已被绑定，请换一个邮箱再发送！");
        }else{
            String name = "email:"+email+"_code:";
            String code = sendEmail.sendEmail(email);
            stringRedisTemplate.opsForValue().set(name, JSONUtil.toJsonStr(code), 2, TimeUnit.MINUTES);
            return new R(true, code);
        }

    }

    /**
     * 邮箱注册
     */
    @PostMapping("/addOneByEmial/{email}/{code}")
    public R emailRegister(@PathVariable String email, @PathVariable String code){
        String codeRedis = stringRedisTemplate.opsForValue().get("email:"+email+"_code:");
        if(StrUtil.isNotBlank(codeRedis)){
           String code1 = JSON.parseObject(codeRedis, String.class);
            if (code.equals(code1)){
                User user = new User();
                user.setUsername(email);
                user.setEmail(email);
                user.setPassword(email);
                user.setPhone(null);
                userService.save(user);
                return new R(true, "注册成功");
            }
        }
        return new R(false, "验证码已过期或者错误");

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
