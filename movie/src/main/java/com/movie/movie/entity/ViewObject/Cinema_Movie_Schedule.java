package com.movie.movie.entity.ViewObject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;



/**
 * 用在影院中查看详情的实体
 */
@Data
public class Cinema_Movie_Schedule implements Serializable {

    /**
     * 影院id
     */

    private Integer cinemaId;

    /**
     * 影院名字
     */
    private String cinemaName;


    /**
     * 影片名字
     */
    private String movieName;

    /**
     *影片开始实践
     */
    private LocalDateTime scheduleBegintime;

    /**
     * 影片结束实践
     */
    private LocalDateTime scheduleEndtime;

    /**
     * 票价
     */
    private Double price;
}
