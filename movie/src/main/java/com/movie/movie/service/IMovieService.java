package com.movie.movie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.entity.Cinema;
import com.movie.movie.entity.Movie;
import com.baomidou.mybatisplus.extension.service.IService;
import com.movie.movie.entity.ViewObject.Movie_Cinema_Schedule;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
public interface IMovieService extends IService<Movie> {

    /**
     * 查询某个影片在影院的安排
     */
    List<Movie_Cinema_Schedule> getMovie_Cinema_Schedule(Integer movieId);

    /**
     * 模糊查询某个类型
     */
    List<Movie> getMovieByType(String movieTypes);

    /**
     *分页查询
     */
    IPage<Movie> getPage(int currentPage, int pageSize, Movie movie);

    Boolean updateMovieStatus(Integer movieId, Integer movieStatus);

}
