package com.movie.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.movie.entity.Schedule;
import com.movie.movie.entity.Seat;
import com.movie.movie.entity.ViewObject.Schedule_Cinema_Movie;
import com.movie.movie.mapper.ScheduleMapper;
import com.movie.movie.mapper.SeatMapper;
import com.movie.movie.service.IScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.movie.movie.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements IScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ISeatService seatService;


    @Override
    public List<Schedule_Cinema_Movie> getSchedule_Cinema_Movie(String CinemaName) {
        return scheduleMapper.getSchedule_Cinema_Movie(CinemaName);
    }


    @Override
    @Transactional
    public Boolean saveScheduleAndAddSeat(Schedule schedule)  {
        //插入一条计划
        int insert = scheduleMapper.insert(schedule);
        //存储全部位置
        List<Seat> list = new ArrayList<>();
        //存储一个位置
        Seat seat = new Seat();
        if (insert > 0){
            //查询安排表中  yingpianid和影院id相等的数据
           QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
           queryWrapper.eq("cinema_id", schedule.getCinemaId());
           queryWrapper.eq("movie_id", schedule.getMovieId());

           //循环生成对应的位置数  row  col
            for (int i = 1; i <= Integer.parseInt(schedule.getSeatRow()) ; i++){
                for (int j = 1; j <= Integer.parseInt(schedule.getSeatCol()) ; j++){
                    seat.setSeatId(null);
                    seat.setScheduleId(getOne(queryWrapper).getScheduleId());
                    seat.setSeatRow(i);
                    seat.setSeatCol(j);
                    seat.setSeatStatus(0);
                    //把位置加进集合
                    list.add(seat);
                    //功能类似置空
                    seat = new Seat();
                }
            }

        }
        //执行插入位置，全部插入返回true
        return seatService.saveBatch(list);
    }

}
