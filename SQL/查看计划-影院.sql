SELECT SCHEDULE
	.schedule_id,
	SCHEDULE.cinema_id,
	SCHEDULE.movie_id,
	SCHEDULE.schedule_begintime,
	SCHEDULE.schedule_endtime,
	SCHEDULE.price,
	cinema.cinema_name,
	movie.movie_name 
FROM
	SCHEDULE,
	cinema,
	movie 
WHERE
	cinema.cinema_name = '王侯将相影院' 
	AND SCHEDULE.cinema_id = cinema.cinema_id 
	AND SCHEDULE.movie_id = movie.movie_id