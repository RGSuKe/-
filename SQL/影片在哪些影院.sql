SELECT
	movie.movie_id,
	movie.movie_name,
	movie.movie_img,
  cinema.cinema_id,
	cinema.cinema_name,
	
	SCHEDULE.schedule_begintime,
	SCHEDULE.schedule_endtime,
	SCHEDULE.price 
FROM	
	`cinema` inner join
	`movie`  inner join 
	`schedule`
WHERE
	movie.movie_id = 1 
	AND  SCHEDULE.cinema_id=cinema.cinema_id
	AND SCHEDULE.movie_id=movie.movie_id