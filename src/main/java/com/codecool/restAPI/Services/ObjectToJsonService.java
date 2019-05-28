package com.codecool.restAPI.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjectToJsonService {

    // REMOVE THIS LATER IN REFACTOR PHASE
//    public static void main(String[] a) {
//         main for tests
//
//         Creating object of POJOONE
//        List<String> listString = new ArrayList<>();
//        listString.add("one");
//        listString.add("kta");
//        listString.add("zdpa");
//        POJOONE org = new POJOONE("testname", "testdescription", new POJO2("Name2", "description", 2), 10, listString);
//        String jsonString = null;
//        try {
//            jsonString = new ObjectToJsonService().convertObjectToJson(org);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(jsonString);
//    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        return jsonString;
    }

    // Alternative way, REMOVE IN REFACTOR PHASE
//    public String convertObjectToJson(Object object) throws JsonProcessingException {
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        return ow.writeValueAsString(object);
//    }
}