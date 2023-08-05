package com.rasha.movieInfo.controller;

import com.rasha.movieInfo.bean.MovieInfoDto;
import com.rasha.movieInfo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/save")
    public ResponseEntity<List<MovieInfoDto>> saveMovies() {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveOmDbInfo());
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieInfoDto>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<List<MovieInfoDto>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.findByTitle(title));
    }
}