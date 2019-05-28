package com.codecool.restAPI;

import com.codecool.restAPI.Models.Kernel;
import com.codecool.restAPI.Services.KernelService;

import java.util.List;

public class App {
    public static void main(String[] args) {

        KernelService kernelService = new KernelService();
        Kernel kernel1 = new Kernel("1", "kernel1", "Kernel1Description");
        Kernel kernel2 = new Kernel("2", "kernel2", "Kernel2Description");
        Kernel kernel3 = new Kernel("3", "kernel3", "Kernel3Description");

        System.out.println("!!!PERSIST - START!!!!");

        kernelService.persist(kernel1);
        kernelService.persist(kernel2);
        kernelService.persist(kernel3);

        List<Kernel> kernels1 = kernelService.findAll();
        System.out.println("Kernels Persisted are :");
        for (Kernel k : kernels1) {
            System.out.println("-" + k.toString());
        }

        System.out.println("*** Persist - end ***");
        System.out.println("*** Update - start ***");

        kernel1.setName("keeernel1");
        System.out.println(kernel1.getName());
        kernelService.update(kernel1);

        System.out.println("KERNEL Updated is =>" + kernelService.findById(kernel1.getId()).toString());
        System.out.println("*** Update - end ***");
        System.out.println("*** Find - start ***");

        String id1 = kernel1.getId();
        Kernel another = kernelService.findById(id1);

        System.out.println("KERNEL found with id " + id1 + " is =>" + another.toString());
        System.out.println("*** Find - end ***");
        System.out.println("*** Delete - start ***");

        String id3 = kernel3.getId();
        kernelService.delete(id3);

        System.out.println("Deleted kernel with id " + id3 + ".");
        System.out.println("Now all books are " + kernelService.findAll().size() + ".");
        System.out.println("*** Delete - end ***");
        System.out.println("*** FindAll - start ***");

        List<Kernel> books2 = kernelService.findAll();
        System.out.println("KERNEL found are :");
        for (Kernel k : books2) {
            System.out.println("-" + k.toString());
        }

        System.out.println("*** FindAll - end ***");
        System.out.println("*** DeleteAll - start ***");

        kernelService.deleteAll();

        System.out.println("KERNELS found are now " + kernelService.findAll().size());
        System.out.println("*** DeleteAll - end ***");

        System.exit(0);
    }


}
