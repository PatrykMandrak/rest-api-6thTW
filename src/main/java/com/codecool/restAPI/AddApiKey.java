package com.codecool.restAPI;

import com.codecool.restAPI.Models.ApiKey;
import com.codecool.restAPI.Services.ApiKeyModelService;

import java.util.Scanner;

public class AddApiKey {
    private static Scanner scanner = new Scanner(System.in);
    private static ApiKeyModelService apiKeyService = new ApiKeyModelService();

    public static void main(String[] args) {
        System.out.println("WELCOME TO ULTRA ADMINISTRATOR PANEL PRO");

        ApiKeyModelService apiKeyService = new ApiKeyModelService();

        String userInput;

        boolean addNew = true;
        while (addNew) {
            System.out.print("Enter new api key: ");
            userInput = scanner.next();
            addNewKey(userInput);
            if (!addAnother()) {
                addNew = false;
            }
        }
        System.exit(0);
    }

    private static boolean addAnother() {
        String userInput = "";

        boolean wrongChoice = true;
        while (wrongChoice) {
            System.out.print("Add another(y/n)?: ");
            userInput = scanner.next().toLowerCase();
            System.out.println();
            if (userInput.equals("y") || userInput.equals("n")) {
                wrongChoice = false;
            } else {
                System.out.println("Answer only \"y\" or \"n\"");
            }
        }

        if (userInput.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    private static void addNewKey(String newKey) {
        ApiKey apiKey = new ApiKey(newKey);
        apiKeyService.persist(apiKey);
    }
}
