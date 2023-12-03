package com.sparta.jarjarbinks.worldproject.controller;

import com.sparta.jarjarbinks.worldproject.model.services.ApiKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIKeyController {

    private ApiKeyService keyService;

    @Autowired
    public APIKeyController(ApiKeyService keyService) {
        this.keyService = keyService;
    }

    @Operation(summary = "Generate a new 8 lettered API key")
    @Tag(name = "Key API")
    @GetMapping("/api-key")
    public String getNewApiKey() {
        return keyService.genApiKey();
    }

    @Operation(summary = "Check if an 8 lettered API key is part of the database.")
    @Tag(name = "Key API")
    @GetMapping("/api-key/{key}")
    public boolean checkApiKey(@PathVariable String key) {
        return keyService.checkApiKey(key);
    }



}
