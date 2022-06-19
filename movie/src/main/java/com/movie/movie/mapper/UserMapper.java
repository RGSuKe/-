package com.movie.movie.mapper;

import com.movie.movie.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
