package com.movie.movie.controller;


import com.movie.movie.controller.util.R;
import com.movie.movie.entity.Schedule;
import com.movie.movie.service.IScheduleService;
import com.movie.movie.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
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
@CrossOrigin
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private ISeatService seatService;



    /**
     * 影院方查询计划
     */
    @GetMapping("/getSchedule/{CinemaName}")
    public R getCinema(@PathVariable String CinemaName){
       return new R(true, scheduleService.getSchedule_Cinema_Movie(CinemaName));
    }

    /**
     * 影院方删除计划
     */
    @PostMapping("/deleteSchedule")
    @Transactional
    public R deleteSchedule(@RequestBody List<Integer> scheduleIds){
        //删除对应计划的座位
        seatService.removeSeatByScheduleId(scheduleIds);
        return new R(true, scheduleService.removeByIds(scheduleIds));

    }

    /**
     * 添加一个计划，并插入对应的位置
     */
    @PostMapping("/addSchedule")
    public R addSchedule(@RequestBody Schedule schedule) throws InterruptedException {
        return new R(true, scheduleService.saveScheduleAndAddSeat(schedule));
    }

    /**
     * 更新计划
     */
    @PostMapping("/updateSchedule")
    public R updateSchedule(@RequestBody Schedule schedule){
        System.out.println(schedule);
        return new R(true, scheduleService.saveOrUpdate(schedule));
    }

}
