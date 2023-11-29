package com.sparta.jarjarbinks.worldproject.model.repositories;

import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountrylanguageRepository extends JpaRepository<CountrylanguageDTO, CountrylanguageIdDTO> {

    void deleteById(Integer id);
    List<CountrylanguageDTO> findAllByCountryCodeAndOrderByPercentageDesc(CountryDTO countryDTO);
}