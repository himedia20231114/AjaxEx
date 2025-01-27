package com.example.AjaxEX.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AjaxEX.dto.MovieDTO;
import com.example.AjaxEX.entity.Movie;
import com.example.AjaxEX.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	// MovieService : 보안 , 중복코드 방지, 모듈화 (메소드)  
	//     Controller   ===>   Service ===> Repository 
	// 호출 : Controller 
	// 제어 : Repository 
	
	
	// DI (의존성 주입 : 스프링 프레임 워크에 객체를 주입 ) <== @RequiredArgsConstructor
	private final MovieRepository movieRepository; 
	
	//  DB에 값을 insert 메소드
	public String movieInsert (MovieDTO movieDTO) {
		
		// MovieDTO : Client  <===> MovieDTO <===> Movie  (Entity) ==. MovieRepository ==> DB 
		
		// movieDTO의 모든 필드의 값을 Movie (Entiy) 로 주입 
		Movie movie = movieDTO.createMovie(); 
		
		System.out.println("===Movie (Entity 의 값을 출력 ) ====");
		System.out.println(movie.getTitle());
		System.out.println(movie.getPoster_path());
		System.out.println(movie.getRelease_date());
		System.out.println(movie.getVote_count());
		System.out.println("===Movie (Entity 의 값을 출력 ) ====");
		
		movieRepository.save(movie); 
		
		return "insert 성공됨 !!!!" ; 
	}
	
	// DB의 movie 테이블의 모든 레코드를 List<Movie>  가지고 와서 List<MovieDTO> 
	public List<MovieDTO> selectAll (){
		//System.out.println("movieServer 호출됨!!!");
		
		//
		List<MovieDTO> movieList = new ArrayList(); 
		
		// DB에서 모든 레코드를 가지고 옮 
		List<Movie> movieListDB = movieRepository.findAll(); 
		// 출력 
//		for (int i = 0 ; i < movieListDB.size() ; i++) {
//			Movie movie = movieListDB.get(i); 
//			System.out.println("===== " + i  + "=====");
//			System.out.println(movie.getTitle());
//			System.out.println(movie.getPoster_path());
//			System.out.println(movie.getVote_count());
//			System.out.println(movie.getOriginal_language());
//			System.out.println("=======================");
//			
//		}
		
		// List<Movie> movieListDB  ===> List<MovieDTO> movieList
		// 주의 : List 는 for 문 밖에서 선언, 
		//       // List 에 넣을 객체는 for문 안에서 선언되어야 객체 주소가 각각 다를게 만들어진다. 
		for (int i = 0 ; i < movieListDB.size(); i++) {
			Movie movie = movieListDB.get(i);
			MovieDTO movieDTO = new MovieDTO(); 
			// movie Entity 의 값을 movieDTO에 주입 
			movieDTO = movieDTO.of(movie); 
			movieList.add(movieDTO); 	
		}
		
		
		return movieList ; 
	}
	
	// 수정 : put ,   매개변수 : id (long) , MovieDTO 
	public void updateMovie(long id , MovieDTO movieDTO) {
		// id 에대한 Movie 객체를 가지고 옴. 
		Optional<Movie> op = movieRepository.findById(id); 
		Movie movie = op.get(); 
		
//		System.out.println(movie.getTitle());
//		System.out.println(movie.getVote_count());
		
		// Movie 객체에 movieDTO 
//		movie = movieDTO.createMovie(); 
		
		 movie.setTitle(movieDTO.getTitle());
		 movie.setVote_count(movieDTO.getVote_count());
		 movie.setOriginal_language(movieDTO.getOriginal_language());
		
		
		// save(Movie) // 수정 완료 
		movieRepository.save(movie); 
		
	}
	
	// 삭제 메소드 : 리턴 : void , 매개변수 : id
	public void deleteMovie (long id) {
		
		// id를 인풋 받아서 findById(id) 
		Optional<Movie> op = 
				movieRepository.findById(id); 
		Movie movie = null ; 
		try {
			// op 가 Null 일 경우 예외 처리가 필요  
			 movie = op.get(); 
		}catch (Exception e) {
			
		}
		
		//System.out.println(movie.getTitle());
		
		movieRepository.delete(movie);
		
	//	System.out.println("DB에서 삭제 성공됨!!!");
		
	}
	
	// 상세보기 : 리턴 : MovieDTO , 매개변수 : id 
	public MovieDTO getMovieDetail (long id) {
		// 
		// id를 인풋 받아서 findById(id) 
		Optional<Movie> op = 
				movieRepository.findById(id); 
		Movie movie = null ; 
		try {
			// op 가 Null 일 경우 예외 처리가 필요  
			 movie = op.get(); 
		}catch (Exception e) {
			
		}
		
		System.out.println("movie 객체 출력");
		System.out.println(movie.getTitle());
		
		// movie (Entity) ===> MovieDTO  매핑 
		MovieDTO movieDTO = new MovieDTO(); 
		
		movieDTO = movieDTO.of(movie); 
		
		System.out.println("movieDTO 의 값 출력");
		System.out.println(movieDTO.getTitle());
		
		
		
		return movieDTO ; 
	}
	

	
	
}
