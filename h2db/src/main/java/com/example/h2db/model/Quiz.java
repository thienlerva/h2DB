package com.example.h2db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Quiz {
    @JsonProperty("response_code")
    int responseCode;
    List<Result> results;
}
