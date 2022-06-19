package com.movie.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.movie.movie.entity.Order1;
import com.movie.movie.entity.ViewObject.OrderVo;
import com.movie.movie.entity.Seat;
import com.movie.movie.entity.ViewObject.OrderVo2;
import com.movie.movie.mapper.OrderMapper;
import com.movie.movie.mapper.SeatMapper;
import com.movie.movie.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order1> implements IOrderService {

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 创建一个订单  使用OrderVo接收前端传回来的数据
     *              OrderVo的 seatId 类型 ArrayList ：因为可能选了多个座位
     *              因为OrderVo实体不能直接插入数据库，所以作为一个中转
     *              Order1的seatId 类型 Integer ：需要一个一个的插入数据库
     */
    @Override
    @Transactional
    public Boolean addOrderAndChangeSeatStatus(OrderVo orderVo) {
        //插入用户id  未登录时采用，现在登录了 ，不用了
//        User user = new User();
//        user.setUserId(1);
        System.out.println(orderVo.toString());

        //创建一个List，存入一次性下单了多少个位置
        List<Order1> list = new ArrayList<>();
        for (int i = 0; i < orderVo.getSeatId().size(); i++){
            //创建数据库对应的实体  Order1，然后获取前端传回来的值
            Order1 order1 = new Order1();
//            order1.setUserId(user.getUserId());
            order1.setUserId(orderVo.getUserId());
            order1.setOrderCreatetime(LocalDateTime.now());
            order1.setScheduleId(orderVo.getScheduleId());
            order1.setOrderStatus(2);
            /**
             从前端传回来的实体中获取选中 座位的id
             因为传回来的座位id是一个 OrderVo  seatId ArrayList
             然后用Order1 seatId Integer 一个一个接收 ，然后再一个一个放入List，最后使用MP提供的 批量插入的方法
             **/
            order1.setSeatId((Integer) orderVo.getSeatId().get(i));
            System.out.println("座位ID："+order1.getSeatId());
            list.add(order1);
        }

        System.out.println(list);

        boolean orderinsert = saveBatch(list);

        //修改位置的状态
        if (orderinsert == true){
            Seat seat = new Seat();
            seat.setSeatStatus(1);
            for (int i = 0; i < orderVo.getSeatId().size(); i++){
                UpdateWrapper<Seat> seatUpdateLambda = new UpdateWrapper <>();
                seatUpdateLambda.eq("seat_id", (Integer) orderVo.getSeatId().get(i));
                seatUpdateLambda.set("seat_status", 1);
                seatMapper.update(null,seatUpdateLambda );
            }
        }
        return orderinsert;
    }

    //分页查询
    @Override
    public IPage<OrderVo2> getPage(int currentPage, int pageSize, OrderVo2 orderVo2) {
        IPage<OrderVo2> page = new Page(currentPage, pageSize);
        String username = orderVo2.getUsername();
        System.out.println("111111111111111111111111111111111111111111111111"+username);
        return orderMapper.getPage(page, username);
    }

    //取消订单-修改状态为0
    @Override
    @Transactional
    public Boolean cancelOrder(List<OrderVo2> order) {

//        int update = 0;
//        for (int i = 0; i < order.size(); i++){
//            //改变订单的状态
//            UpdateWrapper<Order1> updateWrapper = new UpdateWrapper<>();
//            updateWrapper.eq("order_id", order.get(i).getOrderId());
//            updateWrapper.set("order_status", 0);
//            orderMapper.update(null, updateWrapper);
//            //改变座位的状态
//            UpdateWrapper<Seat> updateWrapper1 = new UpdateWrapper<>();
//            updateWrapper1.eq("seat_id",order.get(i).getSeatId());
//            updateWrapper1.set("seat_status", 0);
//            seatMapper.update(null, updateWrapper1);
//            update = update + 1;
//        }
        //使用封装的方法
         this.changeOrderAndSaetStatus(order, 0, 0);
         return true ;
    }

    //完成订单-修改状态为1
    @Override
    @Transactional
    public Boolean finishOrder(List<OrderVo2> order) {
        this.changeOrderAndSaetStatus(order, 1, 0) ;
        return  true ;
    }

    public Integer changeOrderAndSaetStatus(List<OrderVo2> order, int orderStatus, int seatStatus){
        int count = 0;
        for (int i = 0; i < order.size(); i++){
            //改变订单状态
            UpdateWrapper<Order1> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("order_id", order.get(i).getOrderId());
            updateWrapper.set("order_status", orderStatus);
            orderMapper.update(null, updateWrapper);
            //改变座位的状态
            UpdateWrapper<Seat> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.eq("seat_id",order.get(i).getSeatId());
            updateWrapper1.set("seat_status", seatStatus);
            seatMapper.update(null, updateWrapper1);
            count = count + 1;
        }
        return count;
    }

}
