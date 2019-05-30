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
    private OperationSystemService operationSystemService = new OperationSystemService();
    private DefaultDesktopEnvironmentService defaultDesktopEnvironmentService = new DefaultDesktopEnvironmentService();
    private KernelService kernelService = new KernelService();
    private KernelTypeService kernelTypeService = new KernelTypeService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        List<String> splittedUri = getFixedSplittedUri(uri);
        String apiKey = splittedUri.get(0); // apiKEY

        String stringResponse;
        if (checkIfValidKey(apiKey)) {
            stringResponse = getEntityString(splittedUri);
            ///
        } else {
            stringResponse = "Invalid Key";
        }

        System.out.println(uri);
        response.getWriter().write(stringResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        List<String> splittedUri = getFixedSplittedUri(uri);
        String apiKey = splittedUri.get(0); // apiKEY

        String stringResponse;
        if (checkIfValidKey(apiKey)) {
            stringResponse = addNewEntity(splittedUri, request);
        } else {
            stringResponse = "Invalid Key";
        }

        System.out.println(uri);
        response.getWriter().write(stringResponse);


    }

    @Override
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        List<String> splittedUri = getFixedSplittedUri(uri);
        String apiKey = splittedUri.get(0); // apiKEY

        String stringResponse;
        if(checkIfValidKey(apiKey)) {
            stringResponse = updateEntity(splittedUri, request);
        } else {
            stringResponse = "Invalid Key";
        }

        System.out.println(uri);
        response.getWriter().write(stringResponse);


    }

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        List<String> splittedUri = getFixedSplittedUri(uri);
        String apiKey = splittedUri.get(0);

        String stringResponse;
        if (checkIfValidKey(apiKey)) {
            stringResponse = deleteEntity(splittedUri, request);
        } else {
            stringResponse = "Invalid Uri";
        }

        System.out.println(uri);
        response.getWriter().write(stringResponse);
    }

    private boolean checkIfValidKey(String key) {
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

        return "invalidElementProvided";
    }

    private String updateEntity(List<String> splittedUri, HttpServletRequest request) {
        String elementTypeString = splittedUri.get(1);

        switch (elementTypeString) {
            case "operationSystems":
                return operationSystemService.updateOperationSystem(request);
            case "kernels":
                return kernelService.updateKernel(request);
            case "kernelTypes":
                return kernelTypeService.updateKernelType(request);
            case "defaultDesktopEnvironments":
                return defaultDesktopEnvironmentService.udateDefaultDesktopEnvironments(request);
        }

        return "invalidElementProvided";
    }


    private String deleteEntity(List<String> splittedUri, HttpServletRequest request) {
        String elementTypeString = splittedUri.get(1);

        switch (elementTypeString) {
            case "operationSystems":
                return operationSystemService.deleteOperationSystem(request);
            case "kernels":
                return kernelService.deleteKernel(request);
            case "kernelTypes":
                return kernelTypeService.deleteKernelType(request);
            case "defaultDesktopEnvironments":
                return defaultDesktopEnvironmentService.deleteDesktopEnvironment(request);
        }

        return "no such case";
    }
}