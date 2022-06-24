package com.movie.movie.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.controller.util.R;
import com.movie.movie.entity.Cinema;
import com.movie.movie.entity.ViewObject.Cinema_Movie_Schedule;
import com.movie.movie.service.ICinemaService;
import com.movie.movie.utils.RedisUtils;
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
 * @CrossOrigin ：解决跨域问题。可使用注解，也可使用自己定义的配置类 “CorsConfig”
 */
@CrossOrigin
@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private ICinemaService cinemaService;

    //现在采用下面的StringRedisTemplate
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 查询全部影院
     * @return
     */
    @GetMapping
    public R getAll(){
        return new R(true,cinemaService.list());
    }

    /**
     * 查询某个影院
     */
    @GetMapping("{id}")
    public R getByIdone(@PathVariable Integer id){
        System.out.println("请求了按Id查询："+id);

        //StringRedisTemplate提供的
        String cinemaRedis = stringRedisTemplate.opsForValue().get("Cinema_cinemaId:"+id.toString());
        if (StrUtil.isNotBlank(cinemaRedis)) {
            //存在，直接返回
            System.out.println("请求了redis-----------------------getByIdone");
            Cinema cinemaRedisReturn =  JSONUtil.toBean(cinemaRedis, Cinema.class);
//            Cinema cinemaRedisReturn =  JSON.parseObject(cinemaRedis, Cinema.class);
            return new R(true, cinemaRedisReturn);
        }else{
            //查询数据库
            Cinema cinemaMysql = cinemaService.getById(id);
            if (cinemaMysql == null){
                return null;
            }
            //将数据存入redis
            stringRedisTemplate.opsForValue().set("Cinema_cinemaId:"+cinemaMysql.getCinemaId().toString(), JSONUtil.toJsonStr(cinemaMysql), 5, TimeUnit.MINUTES);
            System.out.println("请求了mysql-------------------------getByIdone");
            return new R(true,cinemaMysql);
        }

        //自定义的工具 RedisUtils
        //查询redis的
//        Cinema cinemaRedis = (Cinema) redisUtils.get("Cinema_cinemaId:"+id.toString());
//        //查询redis为空
//        if (cinemaRedis != null){
//            System.out.println("请求了redis");
//            return new R(true,cinemaRedis);
//        }else{
//            //查询数据库
//            Cinema cinemaMysql = cinemaService.getById(id);
//            if (cinemaMysql == null){
//                return null;
//            }
//            //将数据存入redis
//            redisUtils.set("Cinema_cinemaId:"+cinemaMysql.getCinemaId().toString(), cinemaMysql);
//            System.out.println("请求了mysql");
//            return new R(true,cinemaMysql);
//        }

            //直接mysql，不使用redis
//        return new R(true, cinemaService.getById(id));
    }

    /**
     * 分页查询
     */
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Cinema cinema){
        System.out.println("请求了分页查询");
        IPage<Cinema> page = cinemaService.getPage(currentPage, pageSize, cinema);
        if (currentPage > page.getCurrent()){
            page = cinemaService.getPage((int) page.getPages(), pageSize, cinema);
        }
        return new R(true,page);

    }

    /**
     * 查询某个影院的排片
     */
    @GetMapping("/cinema_movie_schedule/{cinema_id}")
    public R getCinema_Movie_Schedule(@PathVariable Integer cinema_id){
//        System.out.println("请求了“影院-影片-安排“请求");
//        return new R(true,cinemaService.getCinema_Movie_Schedule(cinema_id));

        String cinema_movie_schedule =  stringRedisTemplate.opsForValue().get("Cinema_cinema.movie.schedule:"+cinema_id.toString());
        if (StrUtil.isNotBlank(cinema_movie_schedule)){
            System.out.println("请求了redis------------------------Cinema-getCinema_Movie_Schedule");
            List<Cinema_Movie_Schedule> cinema_movie_scheduleRetuen = JSON.parseObject(cinema_movie_schedule, new TypeReference<List<Cinema_Movie_Schedule>>(){});
            return new R(true, cinema_movie_scheduleRetuen);
        }else{
            List<Cinema_Movie_Schedule> cinema_movie_schedule1 = cinemaService.getCinema_Movie_Schedule(cinema_id);
            if (cinema_movie_schedule1 == null){
                return null;
            }
            //将数据存入redis
            stringRedisTemplate.opsForValue().set("Cinema_cinema.movie.schedule:"+cinema_id, JSONUtil.toJsonStr(cinema_movie_schedule1), 5, TimeUnit.MINUTES);
            System.out.println("请求了mysql--------------------------Cinema-getCinema_Movie_Schedule");
            return new R(true,cinema_movie_schedule1);
        }

    }



    /**
     * 影院方登录
     */
    @PostMapping("/cinemaLogin")
    public R cinemaLogin(@RequestBody Cinema cinema){
        return new R(true,cinemaService.cinenaLogin(cinema));
    }



}
