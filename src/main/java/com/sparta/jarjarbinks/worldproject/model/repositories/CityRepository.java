package com.sparta.jarjarbinks.worldproject.model.repositories;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityDTO, Integer> {

    // List<CityDTO> findCityDTOByCountryCode(String countryCode);

    List<CityDTO> findAllByCountryCode(CountryDTO countryDTO);

}