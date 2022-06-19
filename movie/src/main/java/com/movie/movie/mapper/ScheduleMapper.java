package com.movie.movie.mapper;

import com.movie.movie.entity.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.movie.entity.ViewObject.Schedule_Cinema_Movie;
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
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Select("SELECT schedule.schedule_id, schedule.cinema_id, schedule.movie_id, schedule.schedule_begintime, schedule.schedule_endtime,price,cinema.cinema_name,movie.movie_name " +
            "FROM schedule, cinema, movie " +
            "WHERE cinema.cinema_name = #{CinemaName} AND schedule.cinema_id = cinema.cinema_id AND schedule.movie_id = movie.movie_id")
    List<Schedule_Cinema_Movie> getSchedule_Cinema_Movie(String CinemaName);
}
