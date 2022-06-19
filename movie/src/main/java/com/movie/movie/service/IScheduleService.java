package com.movie.movie.service;

import com.movie.movie.entity.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.movie.movie.entity.ViewObject.Schedule_Cinema_Movie;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
public interface IScheduleService extends IService<Schedule> {

    List<Schedule_Cinema_Movie> getSchedule_Cinema_Movie(String CinemaName);

    Boolean saveScheduleAndAddSeat(Schedule schedule) throws InterruptedException;
}
