package com.codecool.restAPI.Servlets;

import com.codecool.restAPI.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class ApiServlet extends HttpServlet {
    private OperationSystemService operationSystemService = new OperationSystemService();
    private DefaultDesktopEnvironmentService defaultDesktopEnvironmentService = new DefaultDesktopEnvironmentService();
    private KernelService kernelService = new KernelService();
    private KernelTypeService kernelTypeService = new KernelTypeService();
    private ApiKeyService apiKeyService = new ApiKeyService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        Map<String, String> uriMap = mapUriString(uri);
        String apiKey = uriMap.get("apiKey"); // apiKEY

        String stringResponse;
        if (checkIfValidKey(apiKey)) {
            stringResponse = getEntityString(uriMap);
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
        Map<String, String> uriMap = mapUriString(uri);
        String apiKey = uriMap.get("apiKey"); // apiKEY

        String stringResponse;
        if (checkIfValidKey(apiKey)) {
            stringResponse = addNewEntity(uriMap, request);
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
        Map<String, String> uriMap = mapUriString(uri);
        String apiKey = uriMap.get("apiKey"); // apiKEY

        String stringResponse;
        if(checkIfValidKey(apiKey)) {
            stringResponse = updateEntity(uriMap, request);
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
        Map<String, String> uriMap = mapUriString(uri);
        String apiKey = uriMap.get("apiKey");

        String stringResponse;
        if (checkIfValidKey(apiKey)) {
            stringResponse = deleteEntity(uriMap, request);
        } else {
            stringResponse = "Invalid Uri";
        }

        System.out.println(uri);
        response.getWriter().write(stringResponse);
    }

    private boolean checkIfValidKey(String key) {
        return apiKeyService.checkIfKeyExists(key);
    }

    private Map<String, String> mapUriString(String uri) {
        List<String> uriList = new LinkedList<String>(Arrays.asList(uri.split("/")));
        int notNeededInformationIndex = 0;
        uriList.remove(notNeededInformationIndex); // removing empty string
        uriList.remove(notNeededInformationIndex); // removing "api" string

        Map<String, String> uriMap = new HashMap<>();
        int apiKeyIndex = 0;
        int elementTypeIndex = 1;
        int optionalElementIdIndex = 2;
        uriMap.put("apiKey", uriList.get(apiKeyIndex));
        uriMap.put("elementType", uriList.get(elementTypeIndex));
        if(uriList.size()==3) {
            uriMap.put("id", uriList.get(optionalElementIdIndex));
        }
        return uriMap;
    }

    private String getEntityString(Map<String, String> uriMap) {
        String elementTypeString = uriMap.get("elementType");
        try {
            switch (elementTypeString) {
                case "operationSystems":
                    return operationSystemService.getOperationSystemAsJson(uriMap);
                case "kernels":
                    System.out.println("we are in case KERNELS");
                    return kernelService.getKernelAsJson(uriMap);
                case "kernelTypes":
                    return kernelTypeService.getKernelTypeAsJson(uriMap);
                case "defaultDesktopEnvironments":
                    return defaultDesktopEnvironmentService.getDefaultDekstopEnvironmentAsJson(uriMap);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.toString();
        }

        return "invalidElementProvided";
    }

    private String addNewEntity(Map<String, String> uriMap, HttpServletRequest request) {
        String elementTypeString = uriMap.get("elementType");

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

    private String updateEntity(Map<String, String> uriMap, HttpServletRequest request) {
        String elementTypeString = uriMap.get("elementType");

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


    private String deleteEntity(Map<String, String> uriMap, HttpServletRequest request) {
        String elementTypeString = uriMap.get("elementType");

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