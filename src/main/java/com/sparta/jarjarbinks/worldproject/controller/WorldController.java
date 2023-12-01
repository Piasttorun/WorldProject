package com.sparta.jarjarbinks.worldproject.controller;

import com.sparta.jarjarbinks.worldproject.exceptions.AlreadyExistsException;
import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Optional<CityDTO> getCityById(@PathVariable Integer id) {
        CityDTO cities = worldService.getCityById(id).get();
        return Optional.of(cities);
    }

    @GetMapping("/city")
    public List<CityDTO> getCityById() {
        return worldService.getCity();
    }

    @PutMapping("/city/{id}")
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
    public Optional<CountryDTO> getCountryById(@PathVariable String code) {
        Optional<CountryDTO> countries = worldService.getCountryById(code);
        return countries;
    }

    @GetMapping("/country")
    public List<CountryDTO> getCountry() {
        return worldService.getCountry();
    }

    @PutMapping("/country/{id}")
    public Optional<CountryDTO> patchCountry(@RequestBody CountryDTO newCity, @PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        return worldService.putCountry(newCity, String.valueOf(id));
    }

    @DeleteMapping("/country_language")
    public void deleteCountryLanguage(@RequestBody CountrylanguageIdDTO newCountryId) throws NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCountryLanguage(newCountryId);
    }

    @PostMapping("/country_language")
    public void createCountryLanguage(@RequestBody CountrylanguageDTO newCountrylanguage) throws AlreadyExistsException {
        worldService.createCountryLanguage(newCountrylanguage);
    }

    @GetMapping("/country_language")
    public List<CountrylanguageDTO> getCountryLanguage() {
        return worldService.getCountrylanguage();
    }


    @PutMapping("/country_language/{id}")
    public Optional<CountrylanguageDTO> patchCountryLanguage(@RequestBody CountrylanguageDTO newLanguage, @RequestBody CountrylanguageDTO oldLanguage) throws NotFoundException, InvalidArgumentFormatException {
        return worldService.putCountryLanguage(newLanguage, oldLanguage);
    }

    // Special case methods

    // fergus
    @GetMapping("/countriesNoHeadOfState")
    public List<CountryDTO> countriesNoHeadOfState() {
        return worldService.getCountriesNoHeadOfState();
    }

    // uyi
    @GetMapping("/percentagePopulationLargestCity")
    public double percentagePopulationLargestCity(@RequestBody CountryDTO newCountry) {
        return worldService.getPercentagePopulationLargestCity(newCountry);
    }

    // mati
    @GetMapping("/countryMostCities")
    public String countryMostCities() {
        CountryDTO city = worldService.getCountryMostCities();
        return "City name: " + city.getName() + " and amount: " + city.getCities().size() + ".";
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