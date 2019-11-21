package com.example.h2db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Result {

    String category;
    @JsonProperty("type")
    QuestionType questionType;
    @JsonProperty("difficulty")
    DifficultyLevel difficultyLevel;
    @JsonProperty("correct_answer")
    String correctAnswer;
    @JsonProperty("incorrect_answers")
    String[] incorrectAnswers;
}
