select
order1.order_id,
USER.username,
SCHEDULE.schedule_id,
cinema.cinema_name,
movie.movie_name,
SCHEDULE.schedule_begintime,
SCHEDULE.schedule_endtime,
seat.seat_row,
seat.seat_col,
order1.order_createtime,
order1.order_status 
FROM
	USER,
	order1,
	SCHEDULE,
	seat,
	movie,
	cinema 
WHERE
	order1.user_id = USER.user_id 
	AND order1.schedule_id = SCHEDULE.schedule_id 
	AND order1.seat_id = seat.seat_id 
	AND SCHEDULE.movie_id = movie.movie_id 
	AND cinema.cinema_id = SCHEDULE.cinema_id
 and username = 'b'
 