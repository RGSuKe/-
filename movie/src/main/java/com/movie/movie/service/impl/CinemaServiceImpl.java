package com.movie.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.movie.movie.controller.util.Constants;
import com.movie.movie.entity.Cinema;
import com.movie.movie.entity.ViewObject.Cinema_Movie_Schedule;
import com.movie.movie.exception.ServiceException;
import com.movie.movie.mapper.CinemaMapper;
import com.movie.movie.service.ICinemaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
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
public class CinemaServiceImpl extends ServiceImpl<CinemaMapper, Cinema> implements ICinemaService {

    @Autowired
    private CinemaMapper cinemaMapper;

    /**
     * 按名字搜索影片
     * @param currentPage
     * @param pageSize
     * @param cinema
     * @return
     */
    @Override
    public IPage<Cinema> getPage(int currentPage, int pageSize, Cinema cinema) {
        LambdaQueryWrapper<Cinema> lqw = new LambdaQueryWrapper<Cinema>();
        lqw.like(Strings.isNotEmpty(cinema.getCinemaName()), Cinema::getCinemaName, cinema.getCinemaName() );
        lqw.like(Strings.isNotEmpty(cinema.getCinemaAddress()), Cinema::getCinemaAddress, cinema.getCinemaAddress() );
        lqw.like(Strings.isNotEmpty(cinema.getCinemaWorktime()), Cinema::getCinemaWorktime, cinema.getCinemaWorktime() );
        lqw.like(Strings.isNotEmpty(cinema.getCinemaPhone()), Cinema::getCinemaPhone, cinema.getCinemaPhone() );
        IPage page = new Page(currentPage, pageSize);
        cinemaMapper.selectPage(page, lqw);
        return page;
    }

    /**
     * 查询某个影院的排片---该方法转换json数据会很麻烦，我暂时未解决
     */
//    @Override
//    public List<Cinema> getCinema_Movie_Schedule(Integer cinema_id) {
//        return cinemaMapper.getCinema_Movie_Schedule(cinema_id);
//    }

    /**
     * 查询某个影院的排片
     */
    @Override
    public List<Cinema_Movie_Schedule> getCinema_Movie_Schedule(Integer cinema_id) {
        return cinemaMapper.getCinema_Movie_Schedule(cinema_id);
    }

    /**
     * 登录
     * @param cinema
     * @return
     */
    @Override
    public Cinema cinenaLogin(Cinema cinema) {
        Cinema cinemaInfo = getCinemaInfo(cinema);
        if (cinemaInfo != null){
            return cinemaInfo;
        }else{
           throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }


    }

    public Cinema getCinemaInfo(Cinema cinema){
        QueryWrapper<Cinema> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cinema_username", cinema.getCinemaUsername());
        queryWrapper.eq("cinema_password", cinema.getCinemaPassword());
        Cinema cinemaInfo;
        try {
            cinemaInfo = getOne(queryWrapper);
            System.out.println(cinemaInfo);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return cinemaInfo;
    }



}
