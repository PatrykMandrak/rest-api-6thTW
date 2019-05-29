package com.codecool.restAPI.Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(
                "Request..."
        );
        String uri = request.getRequestURI();
        System.out.println(uri);
        response.getWriter().write(uri);
    }
}