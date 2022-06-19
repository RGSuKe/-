package com.movie.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
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
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 影片id
     */
    @TableId(value = "movie_id", type = IdType.AUTO)
    private Integer movieId;

    /**
     * 影片名字
     */
    private String movieName;

    /**
     * 影片封面
     */
    private String movieImg;

    /**
     * 影片导演
     */
    private String movieDirector;

    /**
     * 影片演员
     */
    private String movieActor;

    /**
     * 影片类型
     */
    private String movieTypes;

    /**
     * 影片上映时间
     */
    private LocalDate movieUptime;

    /**
     * 影片时长
     */
    private String movieDuration;

    /**
     * 影片时长
     */
    private String movieDetail;

    /**
     * 影片是否在上映，1为上映，2为未上映。
     */
    private Integer movieStatus;


}
