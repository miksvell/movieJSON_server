package net.guides.springboot.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Movie;
import net.guides.springboot.crud.repository.MovieRepository;
import net.guides.springboot.crud.service.SequenceGeneratorService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/movies")
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long movieId)
			throws ResourceNotFoundException {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));
		return ResponseEntity.ok().body(movie);
	}

	@PostMapping("/movies")
	public Movie createMovie(@Valid @RequestBody Movie movie) {
		movie.setId(sequenceGeneratorService.generateSequence(Movie.SEQUENCE_NAME));
		return movieRepository.save(movie);
	}

	@PutMapping("/movies/{id}/{amount}")
	public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") Long movieId,@PathVariable(value = "amount") Long amount) throws ResourceNotFoundException {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));
		Long tickets = movie.getTickets()-amount;
		movie.setTickets(tickets);
		final Movie updatedMovie = movieRepository.save(movie);
		return ResponseEntity.ok(updatedMovie);
	}

	@DeleteMapping("/movies/{id}")
	public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") Long movieId)
			throws ResourceNotFoundException {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));

		movieRepository.delete(movie);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
