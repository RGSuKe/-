package com.movie.movie.mapper;

import com.movie.movie.entity.Cinema;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.movie.entity.ViewObject.Cinema_Movie_Schedule;
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
public interface CinemaMapper extends BaseMapper<Cinema> {

    /**
     * 查询某个影院的排片---该方法转换json数据会很麻烦，我暂时未解决
     */
//    List<Cinema> getCinema_Movie_Schedule(Integer id);


    /**
     * 查询某个影院的排片
     */
    @Select(" select cinema.cinema_id, cinema.cinema_name, movie.movie_name, SCHEDULE.schedule_begintime, SCHEDULE.schedule_endtime, SCHEDULE.price\n" +
            " from `cinema` inner join`movie` inner join `schedule`\n" +
            " WHERE cinema.cinema_id = #{cinema_id} AND SCHEDULE.cinema_id=cinema.cinema_id AND SCHEDULE.movie_id=movie.movie_id")
    List<Cinema_Movie_Schedule> getCinema_Movie_Schedule(Integer id);


}
