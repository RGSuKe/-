package com.movie.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 *
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;


    private String username;

    private String password;

    private String email;

    private String phone;


}
