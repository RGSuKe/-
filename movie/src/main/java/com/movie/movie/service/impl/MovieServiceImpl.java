package com.movie.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.movie.movie.entity.Movie;
import com.movie.movie.entity.ViewObject.Movie_Cinema_Schedule;
import com.movie.movie.mapper.MovieMapper;
import com.movie.movie.service.IMovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

    @Autowired
    private MovieMapper movieMapper;


    /**
     * 查询某部影片的安排
     */
    @Override
    public List<Movie_Cinema_Schedule> getMovie_Cinema_Schedule(Integer movieId) {
        return movieMapper.getMovie_Cinema_Schedule(movieId);
    }

    /**
     * 模糊查询某个类型
     */
    @Override
    public List<Movie> getMovieByType(String movieTypes) {
        return movieMapper.getMovieByType(movieTypes);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param movie
     * @return
     */
    @Override
    public IPage<Movie> getPage(int currentPage, int pageSize, Movie movie) {
        LambdaQueryWrapper<Movie> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(movie.getMovieName()), Movie::getMovieName, movie.getMovieName());
//        lqw.like(Strings.isNotEmpty(movie.getMovieImg()), Movie::getMovieImg, movie.getMovieImg());
//        lqw.like(Strings.isNotEmpty(movie.getMovieDirector()), Movie::getMovieDirector, movie.getMovieDirector());
//        lqw.like(Strings.isNotEmpty(movie.getMovieActor()), Movie::getMovieActor, movie.getMovieActor());
//        lqw.like(Strings.isNotEmpty(movie.getMovieTypes()), Movie::getMovieTypes, movie.getMovieTypes());
//        lqw.like( Movie::getMovieUptime, movie.getMovieUptime());
//        lqw.like(Strings.isNotEmpty(movie.getMovieDuration()), Movie::getMovieDuration, movie.getMovieDuration());
//        lqw.like(Strings.isNotEmpty(movie.getMovieDetail()), Movie::getMovieDetail, movie.getMovieDetail());
//        lqw.like( Movie::getMovieStatus, movie.getMovieStatus());

        IPage page = new Page(currentPage, pageSize);
        movieMapper.selectPage(page, lqw);
        return page;

    }

    /**
     * 更新电影的状态
     * @param movieId
     * @return
     */
    @Override
    public Boolean updateMovieStatus(Integer movieId, Integer movieStatus) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("movie_id", movieId);
        Movie movie = new Movie();
        int update;
        if (movieStatus == 0){
            movie.setMovieStatus(1);
            update = baseMapper.update(movie, updateWrapper);
        }else{
            movie.setMovieStatus(0);
            update = baseMapper.update(movie, updateWrapper);
        }

        return update > 0 ? true : false;
    }


}
