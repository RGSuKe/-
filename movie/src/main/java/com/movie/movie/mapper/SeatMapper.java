package com.movie.movie.mapper;

import com.movie.movie.entity.Seat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Mapper
public interface SeatMapper extends BaseMapper<Seat> {

    @Select("select schedule.cinema_id,seat.seat_id,seat_row,seat_col,seat_status\n" +
            "from seat,SCHEDULE\n" +
            "where schedule.schedule_id = #{schedule_id} and SCHEDULE.schedule_id = seat.schedule_id")
    List<Seat> getSeatByScheduleId(Integer schedule_id);


}
