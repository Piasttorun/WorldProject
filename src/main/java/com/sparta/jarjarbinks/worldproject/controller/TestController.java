package com.sparta.jarjarbinks.worldproject.controller;

import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final WorldService worldService;
    private final CountryRepository countryRepository;

    @Autowired
    public TestController(WorldService worldService, CountryRepository countryRepository) {
        this.worldService = worldService;
        this.countryRepository = countryRepository;
    }

    @GetMapping("/test")
    public void TestEndPoint() {
        CountryDTO countryDTO = countryRepository.findAll().get(0);
        System.out.println("Country: " + countryDTO.getName());
        System.out.println("Number of people: " + worldService.getNumberOfPopularLanguageSpeakers(countryDTO));

    }

}
