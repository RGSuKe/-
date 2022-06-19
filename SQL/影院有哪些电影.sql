SELECT
  cinema.cinema_id,
	cinema.cinema_name,
	movie.movie_name,
	SCHEDULE.schedule_begintime,
	SCHEDULE.schedule_endtime,
	SCHEDULE.price 
FROM	
	`cinema` inner join
	`movie`  inner join 
	`schedule`
WHERE
	cinema.cinema_id = 1 
	AND  SCHEDULE.cinema_id=cinema.cinema_id
	AND SCHEDULE.movie_id=movie.movie_id