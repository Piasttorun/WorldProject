package com.sparta.jarjarbinks.worldproject.model.repositories;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityDTO, Integer> {

    List<CityDTO> findAllByCountryCode(CountryDTO countryDTO);
    List<CityDTO> findAllByCountryCode(Optional<CountryDTO> countryDTO);
    void deleteAllByCountryCode(Optional<CountryDTO> countryDTO);


}