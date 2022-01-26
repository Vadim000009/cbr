package ru.cbrtb.cbrinterntb.api;

import lombok.Data;

@Data
public class StringResponse {
    private String response;

    public StringResponse() {}

    public StringResponse(String response) {
        this.response = response;
    }

    public String toStringWithNoJSON() {
        return response;
    }
}
