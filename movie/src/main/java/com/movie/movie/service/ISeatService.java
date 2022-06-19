package com.movie.movie.service;

import com.movie.movie.entity.Order1;
import com.movie.movie.entity.Seat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
public interface ISeatService extends IService<Seat> {

    List<Seat> getSeatByScheduleId(Integer schedule_id);

    Boolean removeSeatByScheduleId(List<Integer> scheduleIds);
}
