package com.sparta.jarjarbinks.worldproject.controller;

import com.sparta.jarjarbinks.worldproject.exceptions.AlreadyExistsException;
import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class WorldController {

    private final WorldService worldService;

    @Autowired
    public WorldController(WorldService worldService,
                           CountryRepository countryRepository,
                           CountrylanguageRepository countrylanguageRepository) {
        this.worldService = worldService;
    }

    @DeleteMapping("/city/{id}")
    public void deleteCity(@PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCity(id);
    }

    @PostMapping("/city")
    public void createCity(@RequestBody CityDTO newCity) throws AlreadyExistsException {
        worldService.createCity(newCity);
    }

    @GetMapping("/city/{id}")
    public List<CityDTO> getCityById(@PathVariable Integer id) {
        if (id == null) {
            return worldService.getCity();
        }else {
            List<CityDTO> cities = new ArrayList<>((Collection) worldService.getCityById(id).get());
            return cities;
        }
    }

    @PatchMapping("/city/{id}")
    public Optional<CityDTO> patchCity(@RequestBody CityDTO newCity, @PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        return worldService.putCity(newCity, id);
    }

    @DeleteMapping("/country/{id}")
    public void deleteCountry(@PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCountry(id);
    }

    @PostMapping("/country")
    public void createCountry(@RequestBody CountryDTO newCity) {
        createCountry(newCity);
    }

    @GetMapping("/country/{code}")
    public List<CountryDTO> getCountryById(@PathVariable String code) {
        if (code.isEmpty()) {
            return worldService.getCountry();
        }else {
            List<CountryDTO> countries = new ArrayList<>((Collection) worldService.getCountryById(code).get());
            return countries;
        }
    }

    @PatchMapping("/country/{id}")
    public Optional<CountryDTO> patchCountry(@RequestBody CountryDTO newCity, @PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        return worldService.putCountry(newCity, String.valueOf(id));
    }

    @DeleteMapping("/country_language/{id}")
    public void deleteCountryLanguage(@PathVariable String id) throws NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCountryLanguage(id);
    }

    @PostMapping("/country_language")
    public void createCountryLanguage(@RequestBody CountrylanguageDTO newCountrylanguage) throws AlreadyExistsException {
        worldService.createCountryLanguage(newCountrylanguage);

    }

    @GetMapping("/country_language")
    public List<CountrylanguageDTO> getCountryLanguage() {
        return worldService.getCountrylanguage();
    }

    @PatchMapping("/country_language/{id}")
    public Optional<CountrylanguageDTO> patchCountryLanguage(@RequestBody CountrylanguageDTO newCity, @PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        return worldService.putCountryLanguage(newCity, id);
    }

    // Special case methods

    // fergus
    @GetMapping("/countriesNoHeadOfState")
    public List<CountryDTO> countriesNoHeadOfState() {
        return worldService.getCountriesNoHeadOfState();
    }

    // uyi
    @PostMapping("/percentagePopulationLargestCity")
    public double percentagePopulationLargestCity(@RequestBody CountryDTO newCountry) {
        return worldService.getPercentagePopulationLargestCity(newCountry);
    }

    // mati
    @GetMapping("/countryMostCities")
    public CountryDTO countryMostCities() {
        return worldService.getCountryMostCities();
    }

    // bianca
    @GetMapping("/citiesSmallestPopulation")
    public List<String> districtSmallestPopulation() {
        return worldService.getSmallestPopulationDistricts();
    }

    // affiq
    @GetMapping("/popular-language/{countrycode}")
    public Integer SpeakMostPopularLanguage(@PathVariable String countrycode) throws NotFoundException {
        return worldService.getNumberOfPopularLanguageSpeakers(countrycode);
    }
}