package com.rasha.movieInfo.conversion;

import com.rasha.movieInfo.bean.MovieInfoDto;
import com.rasha.movieInfo.bean.RatingDto;
import com.rasha.movieInfo.model.MovieInfo;
import com.rasha.movieInfo.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class MapperService {

    @Mapping(target = "valueRating", source = "value")
    @Mapping(target = "id", ignore = true)
    public abstract Rating convertRating(RatingDto ratingDto);

    @Mapping(source = "valueRating", target = "value")
    public abstract RatingDto convertRating(Rating rating);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "yearMovie", source = "year")
    public abstract MovieInfo convertMovieInfo(MovieInfoDto movieInfoDto);

    @Mapping(source = "yearMovie", target = "year")
    public abstract MovieInfoDto convertMovieInfoToDto(MovieInfo movieInfo);

    public abstract List<MovieInfoDto> convertMovieInfoToDto(List<MovieInfo> movieInfo);


}
