package com.movie.movie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.movie.controller.util.Constants;
import com.movie.movie.entity.User;
import com.movie.movie.entity.ViewObject.UserDTO;
import com.movie.movie.exception.ServiceException;
import com.movie.movie.mapper.UserMapper;
import com.movie.movie.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.movie.movie.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 判断用户名是否重复
     * @param user
     * @return
     */
    @Override
    public Boolean getUsernames(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username");
        List<User> usernamesList = userMapper.selectList(queryWrapper);
        List<String> usernames = usernamesList.stream().map(User::getUsername).collect(Collectors.toList());

        if (usernames.contains(user.getUsername())){
            return true;
        }else{
            return false;
        }

    }


    /**
     * login
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO login(UserDTO userDTO) {
        //查询账号密码
        User userInfo = getUserInfo(userDTO);
        //密码账号有无问题
        if (userInfo != null){
            BeanUtil.copyProperties(userInfo, userDTO, true);
            String token = TokenUtils.getToken(userInfo.getUserId().toString(), userInfo.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }

    }

    /**
     * 查询用户信息
     * @param userDTO
     * @return
     */
    public User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User userInfo;
        try {
            userInfo = getOne(queryWrapper);
            System.out.println(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            throw  new ServiceException(Constants.CODE_500, "系统错误");
        }
        return userInfo;
    }

}
