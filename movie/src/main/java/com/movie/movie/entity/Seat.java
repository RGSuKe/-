package com.movie.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "seat_id", type = IdType.AUTO)
    private Integer seatId;

    private Integer scheduleId;


    private Integer seatRow;

    private Integer seatCol;

    private Integer seatStatus;


}
