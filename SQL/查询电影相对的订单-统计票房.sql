SELECT
	movie.movie_id,
	movie.movie_name,
	order1.order_price,
	order1.order_status
FROM
	movie,
	SCHEDULE,
	order1 
WHERE
	SCHEDULE.schedule_id = order1.schedule_id 
	AND movie.movie_id = SCHEDULE.movie_id