package com.movie.movie.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.controller.util.R;
import com.movie.movie.entity.Movie;
import com.movie.movie.entity.ViewObject.Movie_Cinema_Schedule;
import com.movie.movie.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 查询全部影片
     * @return
     */
    @GetMapping
    public R getAll(){
        //直接使用mysql
//       return new R(true,movieService.list());

        //使用mysql+redis
        String movieRedis = stringRedisTemplate.opsForValue().get("Movie_all");
        if (StrUtil.isNotBlank(movieRedis)){
            System.out.println("请求了redis--------------------Movie-getAll");
            List<Movie> movieRedisReturn = JSON.parseObject(movieRedis, new TypeReference<List<Movie>>(){});
            return new R(true, movieRedisReturn);
        }else{
            //查询数据库
            List<Movie> movieListMysql = movieService.list();
            if (movieListMysql == null){
                return null;
            }
            stringRedisTemplate.opsForValue().set("Movie_all", JSONUtil.toJsonStr(movieListMysql), 2, TimeUnit.MINUTES);
            System.out.println("请求了mysql--------------------Movie-getAll");
            return new R(true, movieListMysql);
        }

    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        //直接使用mysql
//        return new R(true,movieService.getById(id));

        //使用mysql+redis
        String oneMovieInfoRedis = stringRedisTemplate.opsForValue().get("Movie_movieId:"+id.toString());
        if (StrUtil.isNotBlank(oneMovieInfoRedis)){
            System.out.println("请求了redis--------------------Movie-getById");
            //fastjson这个有bug，不知道什么问题
//            Movie movieRedisReturn = JSON.parseObject(oneMovieInfoRedis, Movie.class);
            //使用单个类hutool工具类JSONUtil
            Movie movieRedisReturn =  JSONUtil.toBean(oneMovieInfoRedis,  Movie.class);
            return new R(true, movieRedisReturn);
        }else{
            //查询数据库
            Movie oneMovieInfoMysql = movieService.getById(id);
            if (oneMovieInfoMysql == null){
                return null;
            }
            stringRedisTemplate.opsForValue().set("Movie_movieId:"+id, JSONUtil.toJsonStr(oneMovieInfoMysql), 5, TimeUnit.MINUTES);
            System.out.println("请求了mysql--------------------Movie-getById");
            return new R(true, oneMovieInfoMysql);
        }

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
        //直接使用mysql
//        System.out.println("请求了“影片-影院-安排“请求");
//        return new R(true, movieService.getMovie_Cinema_Schedule(movieId));

        //使用mysql+redis
        String movie_cinema_schedule = stringRedisTemplate.opsForValue().get("Movie_movie_cinema_schedule:"+movieId.toString());
        if (movie_cinema_schedule != null){
            System.out.println("请求了redis--------------------------getMovie_Cinema_Schedule");
            List<Movie_Cinema_Schedule> movie_cinema_scheduleRetuen = JSON.parseObject(movie_cinema_schedule, new TypeReference<List<Movie_Cinema_Schedule>>(){});
            return new R(true, movie_cinema_scheduleRetuen);
        }else{
            List<Movie_Cinema_Schedule> movie_cinema_schedule1 = movieService.getMovie_Cinema_Schedule(movieId);
            if (movie_cinema_schedule1 == null){
                return null;
            }
            //将数据存入redis
            stringRedisTemplate.opsForValue().set("Movie_movie_cinema_schedule:"+movieId, JSONUtil.toJsonStr(movie_cinema_schedule1), 2, TimeUnit.MINUTES);
            System.out.println("请求了mysql--------------------------getMovie_Cinema_Schedule");
            return new R(true,movie_cinema_schedule1);
        }
    }

    /**
     * 根据类型查询影片   模糊查询
     */
    @GetMapping("/movie_types/{movieTypes}")
    public R getMovie_Types(@PathVariable String movieTypes){
        //直接使用mysql
//        System.out.println("根据类型查询影片   模糊查询");
//        return new R(true, movieService.getMovieByType(movieTypes));

        //使用mysql+redis
        String Movie_Types =  stringRedisTemplate.opsForValue().get("Movie_Types:"+movieTypes);
        if (Movie_Types != null){
            System.out.println("请求了redis---------------------------Movie-getMovie_Types");
            List<Movie> movieByTypeReturn = JSON.parseObject(Movie_Types, new TypeReference<List<Movie>>(){});
            return new R(true, movieByTypeReturn);
        }else{
            List<Movie> movieByType = movieService.getMovieByType(movieTypes);
            if (movieByType == null){
                return null;
            }
            //将数据存入redis
            stringRedisTemplate.opsForValue().set("Movie_Types:"+movieTypes, JSONUtil.toJsonStr(movieByType), 2, TimeUnit.MINUTES);
            System.out.println("请求了mysql---------------------------Movie-getMovie_Types");
            return new R(true,movieByType);
        }
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
