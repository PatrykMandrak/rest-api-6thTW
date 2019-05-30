package com.codecool.restAPI.Servlets;

import com.codecool.restAPI.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ApiServlet extends HttpServlet {
    OperationSystemService operationSystemService = new OperationSystemService();
    DefaultDesktopEnvironmentService defaultDesktopEnvironmentService = new DefaultDesktopEnvironmentService();
    KernelService kernelService = new KernelService();
    KernelTypeService kernelTypeService = new KernelTypeService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        List<String> splittedUri = getFixedSplittedUri(uri);
        String apiKey = splittedUri.get(0); // apiKEY

        String stringResponse;
        if (checkIfValiKey(apiKey)) {
            stringResponse = getEntityString(splittedUri);
            ///
        } else {
            stringResponse = "Invalid Uri";
        }

        System.out.println(uri);
        response.getWriter().write(stringResponse);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        List<String> splittedUri = getFixedSplittedUri(uri);
        String apiKey = splittedUri.get(0); // apiKEY

        String stringResponse;
        if (checkIfValiKey(apiKey)) {
            stringResponse = addNewEntity(splittedUri, request);
        } else {
            stringResponse = "Invalid Uri";
        }

        System.out.println(uri);
        response.getWriter().write(stringResponse);


    }

    private boolean checkIfValiKey(String key) {
        return true;
    }

    private List<String> getFixedSplittedUri(String uri) {
        List<String> fixedUriList = new LinkedList<String>(Arrays.asList(uri.split("/")));
        fixedUriList.remove(0); // ""
        fixedUriList.remove(0); // "api"
        return fixedUriList;
    }

    private String getEntityString(List<String> splittedUri) {
        String elementTypeString = splittedUri.get(1);
        try {
            switch (elementTypeString) {
                case "operationSystems":
                    return operationSystemService.getOperationSystemAsJson(splittedUri);
                case "kernels":
                    System.out.println("we are in case KERNELS");
                    return kernelService.getKernelAsJson(splittedUri);
                case "kernelTypes":
                    return kernelTypeService.getKernelTypeAsJson(splittedUri);
                case "defaultDesktopEnvironments":
                    return defaultDesktopEnvironmentService.getDefaultDekstopEnvironmentAsJson(splittedUri);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.toString();
        }

        return "invalidElementProvided";
    }

    private String addNewEntity(List<String> splittedUri, HttpServletRequest request) {
        String elementTypeString = splittedUri.get(1);

        switch (elementTypeString) {
            case "operationSystems":
                return operationSystemService.addNewOperationSystem(request);
            case "kernels":
                return kernelService.addNewKernel(request);
            case "kernelTypes":
                return kernelTypeService.addNewKernelType(request);
            case "defaultDesktopEnvironments":
                return defaultDesktopEnvironmentService.addNewDefaultDesktopEnvironments(request);
        }

        return "no such case";
    }
}