package com.movie.movie.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.movie.movie.controller.util.R;
import com.movie.movie.entity.Seat;
import com.movie.movie.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private ISeatService seatService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //通过影院id查询座位--用户选中影院时
    @GetMapping("/getSeatByScheduleId/{schedule_id}")
    public R getSeatByCinemaId(@PathVariable Integer schedule_id){
//        return new R(true, seatService.getSeatByScheduleId(schedule_id),"1");

        String seatByCinemaId = stringRedisTemplate.opsForValue().get("SeatsByCinemaId:"+schedule_id);
        if (StrUtil.isNotBlank(seatByCinemaId)){
            System.out.println("请求了redis--------------Seat-getSeatByCinemaId");
            List<Seat> seatByScheduleIdReturn = JSON.parseObject(seatByCinemaId, new TypeReference<List<Seat>>(){});
            return new R(true, seatByScheduleIdReturn);
        }else{
            //查询数据库
            List<Seat> seatsListMysql = seatService.getSeatByScheduleId(schedule_id);
            if (seatsListMysql == null){
                return null;
            }
            stringRedisTemplate.opsForValue().set("SeatsByCinemaId:"+schedule_id, JSONUtil.toJsonStr(seatsListMysql), 5, TimeUnit.MINUTES);
            System.out.println("请求了mysql--------------Seat-getSeatByCinemaId");
            return new R(true, seatsListMysql,"all");
        }

    }



}
