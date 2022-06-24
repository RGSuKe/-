package com.movie.movie.entity.ViewObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 影院方查询实体
 */
@Data
public class OrderVo2 {

    private  Integer orderId;
    private  String  username;
    private  Integer scheduleId;
    private  String cinemaName;
    private  String movieName;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scheduleBegintime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private  LocalDateTime scheduleEndtime;
    private Integer seatId;
    private  Integer seatRow;
    private  Integer seatCol;
    private Integer seatStatus;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private  LocalDateTime orderCreatetime;
    private Double  orderPrice;
    private  Integer orderStatus;
}
