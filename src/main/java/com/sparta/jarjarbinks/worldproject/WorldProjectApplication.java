package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorldProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(CityRepository cityRepository, CountrylanguageRepository countrylanguageRepository, CountryRepository countryRepository) {
		return args -> System.out.println();
	}
}
