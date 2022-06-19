package com.movie.movie.entity.ViewObject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule_Cinema_Movie {

    private Integer scheduleId;

    private Integer cinemaId;

    private Integer movieId;

    private LocalDateTime scheduleBegintime;

    private LocalDateTime scheduleEndtime;

    private Double price;

    private String CinemaName;

    private String MovieName;
}
