package com.movie.movie.entity.ViewObject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Schedule_Cinema_Movie implements Serializable {

    private Integer scheduleId;

    private Integer cinemaId;

    private Integer movieId;


    private LocalDateTime scheduleBegintime;


    private LocalDateTime scheduleEndtime;

    private Double price;

    private String cinemaName;

    private String movieName;
}
