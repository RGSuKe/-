package com.movie.movie.controller;

import com.movie.movie.controller.util.R;
import com.movie.movie.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getSeatByScheduleId/{schedule_id}")
    public R getSeatByCinemaId(@PathVariable Integer schedule_id){
        return new R(true, seatService.getSeatByScheduleId(schedule_id),"1");
    }


}
