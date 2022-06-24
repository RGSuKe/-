package com.movie.movie.entity.ViewObject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户下订单返回的实体
 */
@Data
public class OrderVo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer orderId;

    private Integer userId;

    private Integer scheduleId;

    private ArrayList seatId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderCreatetime;

    private Double  orderPrice;

    private Integer orderStatus;

}
