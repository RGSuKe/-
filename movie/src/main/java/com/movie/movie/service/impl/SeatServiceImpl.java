package com.movie.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.movie.entity.Order1;
import com.movie.movie.entity.Seat;
import com.movie.movie.mapper.OrderMapper;
import com.movie.movie.mapper.SeatMapper;
import com.movie.movie.service.ISeatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements ISeatService {

    @Autowired
    private SeatMapper seatMapper;

    @Override
    public List<Seat> getSeatByScheduleId(Integer schedule_id) {
        return seatMapper.getSeatByScheduleId( schedule_id);
    }

    @Override
    public Boolean removeSeatByScheduleId(List<Integer> scheduleIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        for (int i = 0; i < scheduleIds.size(); i++){
            queryWrapper.eq("schedule_id",scheduleIds.get(i));
        }
        int delete = baseMapper.delete(queryWrapper);
        return delete > 0 ? true : false;
    }

    public Boolean saveSeatBitch(List list){
        return saveBatch(list);
    }


}
