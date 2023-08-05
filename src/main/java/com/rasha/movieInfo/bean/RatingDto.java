package com.rasha.movieInfo.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {

    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;

}
