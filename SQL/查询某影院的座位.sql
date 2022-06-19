SELECT
	schedule.cinema_id,
	seat.seat_id,
	seat_row,
	seat_col,
	seat_status 
FROM
	seat,
SCHEDULE 
WHERE
	SCHEDULE.schedule_id = 5
	AND SCHEDULE.schedule_id = seat.schedule_id
