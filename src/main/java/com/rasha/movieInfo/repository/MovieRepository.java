package com.rasha.movieInfo.repository;

import com.rasha.movieInfo.model.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieInfo, Long> {

    List<MovieInfo> findByTitle(String title);
}
