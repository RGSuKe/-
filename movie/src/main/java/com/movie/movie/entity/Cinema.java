package com.movie.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Getter
@Setter
public class Cinema implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 影院id
     */
    @TableId(value = "cinema_id", type = IdType.AUTO)
    private Integer cinemaId;

    /**
     * 影院名字
     */
    private String cinemaName;

    /**
     * 影院地址
     */
    private String cinemaAddress;

    /**
     * 影院营业时间
     */
    private String cinemaWorktime;

    /**
     * 影院联系电话
     */
    private String cinemaPhone;

    /**
     * 影院联系电话
     */
    private String cinemaUsername;

    /**
     * 影院联系电话
     */
    private String cinemaPassword;

    /**
     * 联表查询使用到  安排表   写在CinemaMapper.xml
     * 因为换了一种方法实现该功能，所以此二者可不用，如果注释此处，xml文件也要同时注释
     */
    @TableField(exist = false)
    private List<Schedule> schedules;

    /**
     * 联表查询使用到  影片表  写在CinemaMapper.xml
     * 因为换了一种方法实现该功能，所以此二者可不用，如果注释此处，xml文件也要同时注释
     */
    @TableField(exist = false)
    private List<Movie> movies;

}
