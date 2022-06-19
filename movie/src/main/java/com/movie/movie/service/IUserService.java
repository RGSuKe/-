package com.movie.movie.service;

import com.movie.movie.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.movie.movie.entity.ViewObject.UserDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
public interface IUserService extends IService<User> {


    Boolean getUsernames(User user);

    UserDTO login(UserDTO userDTO);


}
