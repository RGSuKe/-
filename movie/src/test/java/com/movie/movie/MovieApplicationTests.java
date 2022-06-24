package com.movie.movie;

import com.movie.movie.controller.CinemaController;
import com.movie.movie.entity.Movie;
import com.movie.movie.mapper.MovieMapper;
import com.movie.movie.service.IMovieService;
import com.movie.movie.service.impl.MovieServiceImpl;
import com.movie.movie.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieApplicationTests {

	@Autowired
	private MovieMapper movieMapper;

	@Autowired
	private RedisUtils redisUtils;

	//测试redis
	@Test
	void contextLoads() {
		System.out.println((String) redisUtils.get("welcome"));
	}



}
