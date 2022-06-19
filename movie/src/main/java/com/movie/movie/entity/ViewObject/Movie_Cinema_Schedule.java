package com.movie.movie.entity.ViewObject;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Movie_Cinema_Schedule implements Serializable {

    /**
     * 影片id
     */

    private Integer movieId;

    /**
     * 影片名字
     */
    private String movieName;

    /**
     * 影片名字
     */
    private String movieImg;

    /**
     * 影院名字
     */
    private Integer cinemaId;

    /**
     * 影院名字
     */
    private String cinemaName;

    /**
     * 安排的id
     */
    private Integer scheduleId;

    /**
     *影片开始时间
     */
    private LocalDateTime scheduleBegintime;

    /**
     * 影片结束时间
     */
    private LocalDateTime scheduleEndtime;

    /**
     * 票价
     */
    private Double price;
}
