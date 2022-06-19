package com.movie.movie.controller;

import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.controller.util.R;
import com.movie.movie.entity.Movie;
import com.movie.movie.entity.Order1;
import com.movie.movie.entity.ViewObject.OrderVo;
import com.movie.movie.entity.ViewObject.OrderVo2;
import com.movie.movie.service.*;
import org.springframework.beans.factory.annotation.Autowired;

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
@RestController
@RequestMapping("/order1")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 添加订单
     * @param orderVo
     * @return
     */
    @PostMapping("/addone")
    public R addOrder(@RequestBody OrderVo orderVo) {
        return new R(true, orderService.addOrderAndChangeSeatStatus(orderVo), "订单完成");
    }

    /**
     * 查询订单
     */
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, OrderVo2 orderVo2){
        System.out.println("请求了分页查询");
        IPage<OrderVo2> page = orderService.getPage(currentPage, pageSize, orderVo2);
        if (currentPage > page.getCurrent()){
            page = orderService.getPage((int) page.getPages(), pageSize, orderVo2);
        }
        return new R(true,page);
    }

    /**
     * 取消订单
     */
    @PostMapping("/canceOrder")
    public R canceOrder(@RequestBody List<OrderVo2> orderVo2){
      return new R(true, orderService.cancelOrder(orderVo2));
    }


    /**
     * 完成订单
     */
    @PostMapping("/finishOrder")
    public R finishOrder(@RequestBody List<OrderVo2> orderVo2){
        return new R(true, orderService.finishOrder(orderVo2));
    }


    /**
     * 删除订单
     */
    @PostMapping("/deleteOrder")
    public R deleteOrder(@RequestBody List<Integer> ids){
        return new R(true, orderService.removeByIds(ids));
    }
}
