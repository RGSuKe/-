package com.movie.movie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.entity.Cinema;
import com.baomidou.mybatisplus.extension.service.IService;
import com.movie.movie.entity.ViewObject.Cinema_Movie_Schedule;

import javax.crypto.Cipher;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
public interface ICinemaService extends IService<Cinema> {

    IPage<Cinema> getPage(int currentPage, int pageSize, Cinema cinema);

//    /**
//     * 查询某个影院的排片---该方法转换json数据会很麻烦，我暂时未解决
//     */
//   List<Cinema> getCinema_Movie_Schedule(Integer cinema_id);

    /**
     * 查询某个影院的排片
     */
    List<Cinema_Movie_Schedule> getCinema_Movie_Schedule(Integer cinema_id);

    Cinema cinenaLogin(Cinema cinema);
}
