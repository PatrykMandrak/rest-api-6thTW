package com.codecool.restAPI;

import com.codecool.restAPI.Models.DefaultDesktopEnvironment;
import com.codecool.restAPI.Models.Kernel;
import com.codecool.restAPI.Models.KernelType;
import com.codecool.restAPI.Models.OperationSystem;
import com.codecool.restAPI.Services.DefaultDesktopEnvironmentService;
import com.codecool.restAPI.Services.KernelService;
import com.codecool.restAPI.Services.KernelTypeService;
import com.codecool.restAPI.Services.OperationSystemService;


public class App {
    public static void main(String[] args) {
        System.out.println("dupa");

        KernelService kernelService = new KernelService();
        KernelTypeService kernelTypeService = new KernelTypeService();
        OperationSystemService operationSystemService = new OperationSystemService();
        DefaultDesktopEnvironmentService defaultDesktopEnvironmentService = new DefaultDesktopEnvironmentService();

        KernelType kernelType1 = new KernelType("kernelTypeName1", "kernelTypeDescription1");


        Kernel kernel1 = new Kernel("kernel1", "Kernel1Description", kernelType1);
        Kernel kernel2 = new Kernel("kernel2", "Kernel2Description", kernelType1);
        Kernel kernel3 = new Kernel("kernel3", "Kernel3Description", kernelType1);

        DefaultDesktopEnvironment defaultDesktopEnvironment = new DefaultDesktopEnvironment("defaultDesktopEnvironment");

        OperationSystem operationSystem1 = new OperationSystem("operationSystem1Name", kernel1, defaultDesktopEnvironment);

        kernelType1.addKernel(kernel1);
        kernelType1.addKernel(kernel2);
        kernelType1.addKernel(kernel3);

        System.out.println("!!!PERSIST - START!!!!");

        kernelTypeService.persist(kernelType1);

        kernelService.persist(kernel1);
        kernelService.persist(kernel2);
        kernelService.persist(kernel3);

        defaultDesktopEnvironmentService.persist(defaultDesktopEnvironment);

        operationSystemService.persist(operationSystem1);
    }

}
