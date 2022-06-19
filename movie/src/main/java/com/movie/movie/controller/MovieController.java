package com.movie.movie.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.controller.util.R;
import com.movie.movie.entity.Cinema;
import com.movie.movie.entity.Movie;
import com.movie.movie.service.ICinemaService;
import com.movie.movie.service.IMovieService;
import com.movie.movie.service.ISeatService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private IMovieService movieService;



    /**
     * 查询全部影片
     * @return
     */
    @GetMapping
    public R getAll(){
        return new R(true,movieService.list());
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,movieService.getById(id));
    }

    /**
     * 分页查询
     */
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Movie movie){
        System.out.println("请求了分页查询");
        System.out.println(movie.getMovieName());
        IPage<Movie> page = movieService.getPage(currentPage, pageSize, movie);
        if (currentPage > page.getCurrent()){
            page = movieService.getPage((int) page.getPages(), pageSize, movie);
        }
        return new R(true,page);
    }

    /**
     * 查询某部影片的安排
     */
    @GetMapping("/movie_cinema_schedule/{movieId}")
    public  R getMovie_Cinema_Schedule(@PathVariable Integer movieId){
        System.out.println("请求了“影片-影院-安排“请求");
        return new R(true, movieService.getMovie_Cinema_Schedule(movieId));
    }

    /**
     * 根据类型查询影片   模糊查询
     */
    @GetMapping("/movie_types/{movieTypes}")
    public R getMovie_Types(@PathVariable String movieTypes){
        return new R(true, movieService.getMovieByType(movieTypes));
    }

    /**
     * 影院方添加电影
     */
    @PostMapping("/addMovie")
    public R addMovie(@RequestBody Movie movie){
        return new R(true, movieService.save(movie));
    }

    /**
     * 删除影片
     */
    @PostMapping("/deleteMovie")
    public R deleteMovie(@RequestBody List<Integer> movieIds){
        System.out.println(movieIds);
        return new R(true,movieService.removeByIds(movieIds)) ;
    }

    /**
     * 改变电影的状态
     */
    @PostMapping("/changeMovieStatus/{movieId}/{movieStatus}")
    public R changeStatus(@PathVariable Integer movieId, @PathVariable Integer movieStatus){
        return new R (true, movieService.updateMovieStatus(movieId, movieStatus));
    }

    /**
     * 更新电影的信息
     * @param movie
     * @return
     */
    @PostMapping("/updateMovie")
    public R updateMovie(@RequestBody Movie movie){
        System.out.println(movie);
        return new R (true, movieService.saveOrUpdate(movie));
    }

}
