package com.movie.movie.mapper;

import com.movie.movie.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.movie.entity.ViewObject.Movie_Cinema_Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Repository
@Mapper
public interface MovieMapper extends BaseMapper<Movie> {


    /**
     * 查询某部影片的安排
     */
    @Select("SELECT movie.movie_id, movie.movie_name, movie.movie_img, cinema.cinema_id, cinema.cinema_name, SCHEDULE.schedule_id, SCHEDULE.schedule_begintime, SCHEDULE.schedule_endtime, SCHEDULE.price\n"+
            "FROM `cinema` inner join `movie`  inner join `schedule`\n"+
            "WHERE movie.movie_id =#{movieId} AND  SCHEDULE.cinema_id=cinema.cinema_id AND SCHEDULE.movie_id=movie.movie_id")
    List<Movie_Cinema_Schedule> getMovie_Cinema_Schedule(Integer movieId);


    /**
     * 模糊查询某个类型
     */
    @Select("SELECT * FROM movie WHERE movie_types LIKE CONCAT ('%', #{movieTypes} ,'%')")
    List<Movie> getMovieByType(String movieTypes);

}
