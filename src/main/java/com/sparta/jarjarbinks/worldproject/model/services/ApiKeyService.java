package com.sparta.jarjarbinks.worldproject.model.services;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ApiKeyService {

    List<String> apiKeys = new ArrayList<>();

    public String genApiKey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        apiKeys.add(generatedString);
        return generatedString;
    }

    public boolean checkApiKey(String key) {
        return apiKeys.contains(key);
    }

    public void saveApiKey(String key) {
        // Code to save key
    }

}
