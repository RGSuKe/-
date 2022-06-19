package com.movie.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
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
@Data
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "schedule_id", type = IdType.AUTO)
    private Integer scheduleId;


    private Integer cinemaId;

    private Integer movieId;

    @JsonFormat(shape=JsonFormat.Shape.STRING,  pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scheduleBegintime;

    @JsonFormat(shape=JsonFormat.Shape.STRING,  pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scheduleEndtime;

    private Double price;

    @TableField(exist = false)
    private String seatRow;

    @TableField(exist = false)
    private String seatCol;
}
