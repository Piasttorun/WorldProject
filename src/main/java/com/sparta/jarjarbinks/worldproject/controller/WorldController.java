package com.sparta.jarjarbinks.worldproject.controller;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
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
}