SELECT
	order1.user_id,
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
	`order1`
WHERE
	username like CONCAT('%','b','%')
	AND USER.user_id = ORDER1.user_id 
	AND seat.seat_id = order1.seat_id
	AND schedule.schedule_id = ORDER1.schedule_id
	AND schedule.cinema_id = cinema.cinema_id
	AND schedule.movie_id = movie.movie_id
	