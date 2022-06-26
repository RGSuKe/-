package com.movie.movie.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.movie.movie.entity.Order1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.movie.entity.Seat;
import com.movie.movie.entity.ViewObject.OrderVo2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小白
 * @since 2022-05-12
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order1> {


    @Select("select order1.order_id, USER.user_id, USER.username, SCHEDULE.schedule_id, cinema.cinema_name, movie.movie_name, SCHEDULE.schedule_begintime, SCHEDULE.schedule_endtime,\n" +
            "seat.seat_id, seat.seat_row, seat.seat_col, seat.seat_status, order1.order_price, order1.order_createtime, order1.order_status \n" +
            "FROM USER, order1, SCHEDULE, seat, movie, cinema \n" +
            "WHERE\n" +
            "\tusername LIKE CONCAT ('%', #{username} ,'%')\n"+
            "\tAND order1.user_id = USER.user_id \n" +
            "\tAND order1.schedule_id = SCHEDULE.schedule_id \n" +
            "\tAND order1.seat_id = seat.seat_id \n" +
            "\tAND SCHEDULE.movie_id = movie.movie_id \n" +
            "\tAND cinema.cinema_id = SCHEDULE.cinema_id \n" )
    IPage<OrderVo2> getPage(IPage<OrderVo2> iPage, String username);


    @Select("SELECT\n" +
            "\tmovie.movie_id,\n" +
            "\tmovie.movie_name,\n" +
            "\torder1.order_price,\n" +
            "\torder1.order_status\n" +
            "FROM\n" +
            "\tmovie,\n" +
            "\tSCHEDULE,\n" +
            "\torder1 \n" +
            "WHERE\n" +
            "\tSCHEDULE.schedule_id = order1.schedule_id \n" +
            "\tAND movie.movie_id = SCHEDULE.movie_id")
    List<OrderVo2> selectPriceByScheduleAndMovie();


//改改select第一个方法就行
    @Select("select order1.order_id, USER.username, SCHEDULE.schedule_id, cinema.cinema_name, movie.movie_name, SCHEDULE.schedule_begintime, SCHEDULE.schedule_endtime,\n" +
            "seat.seat_id, seat.seat_row, seat.seat_col, seat.seat_status, order1.order_price, order1.order_createtime, order1.order_status \n" +
            "FROM USER, order1, SCHEDULE, seat, movie, cinema \n" +
            "WHERE\n" +
            "\torder1.user_id = #{userId}\n"+
            "\tAND order1.user_id = USER.user_id \n" +
            "\tAND order1.schedule_id = SCHEDULE.schedule_id \n" +
            "\tAND order1.seat_id = seat.seat_id \n" +
            "\tAND SCHEDULE.movie_id = movie.movie_id \n" +
            "\tAND cinema.cinema_id = SCHEDULE.cinema_id \n")
    List<OrderVo2> selectMyOrder(Integer userId);
}
