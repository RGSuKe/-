package com.movie.movie.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.controller.util.R;
import com.movie.movie.entity.Cinema;
import com.movie.movie.entity.ViewObject.UserDTO;
import com.movie.movie.service.ICinemaService;
import com.movie.movie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("请求了按Id查询");
        return new R(true, cinemaService.getById(id));
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
//
//    /**
//     * 查询某个影院的排片---该方法转换json数据会很麻烦，我暂时未解决
//     */
//    @GetMapping("/cinema_movie_schedule1/{cinema_id}")
//    public R getCinema_Movie(@PathVariable Integer cinema_id){
//        System.out.println("请求了“影院-影片-安排“请求");
//        return new R(true,cinemaService.getCinema_Movie_Schedule(cinema_id));
//    }

    /**
     * 查询某个影院的排片
     */
    @GetMapping("/cinema_movie_schedule/{cinema_id}")
    public R getCinema_Movie_Schedule(@PathVariable Integer cinema_id){
        System.out.println("请求了“影院-影片-安排“请求");
        return new R(true,cinemaService.getCinema_Movie_Schedule(cinema_id));
    }



    /**
     * 影院方登录
     */
    @PostMapping("/cinemaLogin")
    public R cinemaLogin(@RequestBody Cinema cinema){
        return new R(true,cinemaService.cinenaLogin(cinema));
    }



}
