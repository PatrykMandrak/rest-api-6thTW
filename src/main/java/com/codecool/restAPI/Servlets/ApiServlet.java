package com.codecool.restAPI.Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ApiServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        List<String> splittedUri = getFixedSplittedUri(uri);
        String apiKey = splittedUri.get(0);
        String elementTypeString = splittedUri.get(1);

        String stringResponse = "";
        if(checkIfValiKey(apiKey)) {
            switch(elementTypeString) {
                case "Operation":
                    //donothing
            }
        }
        else {
            stringResponse = "Invalid Uri";
        }

        System.out.println(uri);
        response.getWriter().write(uri);
    }

    private boolean checkIfValiKey(String key) {
        return true;
    }

    private List<String> getFixedSplittedUri(String uri) {
        List<String> fixedUriList = Arrays.asList(uri.split("/"));
        fixedUriList.remove("");
        fixedUriList.remove("api");
        return fixedUriList;
    }
}