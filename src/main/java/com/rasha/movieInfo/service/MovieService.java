package com.rasha.movieInfo.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.rasha.movieInfo.bean.CsvInfoDto;
import com.rasha.movieInfo.bean.MovieInfoDto;
import com.rasha.movieInfo.conversion.MapperService;
import com.rasha.movieInfo.model.MovieInfo;
import com.rasha.movieInfo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

    private List<CsvInfoDto> filteredMovies;
    private final RestTemplateService restTemplateService;
    private final MapperService mapperService;

    private final MovieRepository movieRepository;

    @Value("${omdb.host}")
    private String host;


    @PostConstruct
    public void init() {

        try {
            List<CsvInfoDto> allInfos = new CsvToBeanBuilder(new FileReader(ClassLoader.getSystemResource("awards.csv").getFile())).withType(CsvInfoDto.class).build().parse();
            filteredMovies = allInfos.stream().filter(a -> a.getCategory().equals("Best Picture") && a.getWon().equals("YES")).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            log.error("File Not found: " + e);
        }
    }

    private ResponseEntity<MovieInfoDto> readDataFromOmDb(String title) {
        return restTemplateService.getRestTemplate(host + "t=" + title, MovieInfoDto.class);
    }

    public List<MovieInfoDto> saveOmDbInfo() {
        movieRepository.deleteAll();
        List<MovieInfoDto> movieInfos = new ArrayList<>();
        filteredMovies.stream().forEach(m -> {
            MovieInfoDto movieInfoDto = readDataFromOmDb(m.getNominee()).getBody();
            movieInfoDto.setWon(m.getWon());
            movieInfoDto.setCategory(m.getCategory());
            movieInfoDto.setAdditionalInfo(m.getAdditionalInfo());
            MovieInfo movieInfo = mapperService.convertMovieInfo(movieInfoDto);
            movieRepository.save(movieInfo);
            movieInfos.add(movieInfoDto);
        });
        return movieInfos;
    }

    public List<MovieInfoDto> getMovies() {
        return mapperService.convertMovieInfoToDto(movieRepository.findAll());
    }

    public List<MovieInfoDto> findByTitle(String title) {
        return mapperService.convertMovieInfoToDto(movieRepository.findByTitle(title));
    }
}