package com.movie.movie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.entity.Order1;
import com.baomidou.mybatisplus.extension.service.IService;
import com.movie.movie.entity.ViewObject.OrderVo;
import com.movie.movie.entity.ViewObject.OrderVo2;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
public interface IOrderService extends IService<Order1> {

    Boolean addOrderAndChangeSeatStatus(OrderVo orderVo);

    IPage<OrderVo2> getPage(int currentPage, int pageSize, OrderVo2 orderVo2);

    Boolean cancelOrder(List<OrderVo2> ids);

    Boolean finishOrder(List<OrderVo2> ids);

    List<OrderVo2> selectPriceByScheduleAndMovie();
}
