SELECT
	order.user_id,
	cinema.cinema_name,
	movie.movie_name,
	seat.seat_row,
	seat.seat_col 
FROM
	`user`,
	`cinema`,
	`seat`,
	`movie`,
	`schedule`,
	`order`
WHERE
	order_id = 1
	AND USER.user_id = ORDER.user_id 
	AND seat.seat_id = order.seat_id
	AND schedule.schedule_id = ORDER.schedule_id
	AND schedule.cinema_id = cinema.cinema_id
	AND schedule.movie_id = movie.movie_id
	