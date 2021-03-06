package com.codecool.restAPI.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ObjectToJsonService {
    private ObjectMapper mapper;

    ObjectToJsonService() {
        this.mapper = new ObjectMapper();
    }

    // You can do the same thing with Object Writer
    String convertObjectToJson(Object object) throws JsonProcessingException {
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        return jsonString;
    }
}