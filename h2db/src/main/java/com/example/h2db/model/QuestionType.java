package com.example.h2db.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QuestionType {
    multiple,
    @JsonProperty("boolean")
    yesNo,
}
