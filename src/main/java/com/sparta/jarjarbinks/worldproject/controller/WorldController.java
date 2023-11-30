package com.sparta.jarjarbinks.worldproject.controller;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class WorldController {

    private final WorldService worldService;

    @Autowired
    public WorldController(WorldService worldService) {
        this.worldService = worldService;
    }

    @DeleteMapping("/city/{id}")
    public void deleteCity(@PathVariable Integer id) {
        deleteCity(id);
    }

    @PostMapping("/city")
    public void createCity(@RequestBody CityDTO newCity) {
    }

    @GetMapping("/city")
    public Optional<CityDTO> getCity() {
        return null;
    }

    @PatchMapping("/city/{id}")
    public Optional<CityDTO> patchCity(@RequestBody CityDTO newCity, @PathVariable Integer id) {
        return null;
    }

    @DeleteMapping("/country/{id}")
    public void deleteCountry(@PathVariable Integer id) {
        deleteCountry(id);
    }

    @PostMapping("/country")
    public void createCountry(@RequestBody CountryDTO newCity) {
    }

    @GetMapping("/country")
    public Optional<CountryDTO> getCountry() {
        return null;
    }

    @PatchMapping("/country/{id}")
    public Optional<CountryDTO> patchCountry(@RequestBody CountryDTO newCity, @PathVariable Integer id) {
        return null;
    }

    @DeleteMapping("/country_language/{id}")
    public void deleteCountryLanguage(@PathVariable String id) {
        deleteCountryLanguage(id);
    }

    @PostMapping("/country_language")
    public void createCountryLanguage(@RequestBody CountrylanguageDTO newCity) {
    }

    @GetMapping("/country_language")
    public Optional<CountrylanguageDTO> getCountryLanguage() {
        return null;
    }

    @PatchMapping("/country_language/{id}")
    public Optional<CountrylanguageDTO> patchCountryLanguage(@RequestBody CountrylanguageDTO newCity, @PathVariable Integer id) {
        return null;
    }

    // Special case methods

    // fergus
    @GetMapping("/countriesNoHeadOfState")
    public Optional<CountryDTO> countriesNoHeadOfState() {
        return null;
    }

    // uyi
    @GetMapping("/percentagePopulationLargestCity")
    public Float percentagePopulationLargestCity() {
        return null;
    }

    // mati
    @GetMapping("/countryMostCities")
    public CountryDTO countryMostCities() {
        return null;
    }

    // bianca
    @GetMapping("/citiesSmallestPopulation")
    public List<CountryDTO> citiesSmallestPopulation() {
        return null;
    }

    // affiq
    @GetMapping("/citiesSmallestPopulation/{id}")
    public Integer SpeakMostPopularLanguage(@PathVariable Integer id) {
        return null;
    }
}